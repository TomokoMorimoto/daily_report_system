package actions;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.ShiftView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import services.ShiftService;

/**
 * shiftに関する処理を行うActionクラス
 *
 */
public class ShiftAction extends ActionBase {

    private ShiftService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new ShiftService();

        //メソッドを実行
        invoke();
        service.close();
    }

//    /**
//     * 一覧画面を表示する
//     * @throws ServletException
//     * @throws IOException
//     */
//    public void index() throws ServletException, IOException {
//
//        //指定されたページ数の一覧画面に表示するShiftデータを取得
//        int page = getPage();
//        List<ShiftView> shifts = service.getAllPerPage(page);
//
//        //全日報データの件数を取得
//        long reportsCount = service.countAll();
//
//        putRequestScope(AttributeConst.SHIFTS, shifts); //取得したshiftデータ
//        putRequestScope(AttributeConst.SHI_COUNT, shiftsCount); //全ての日報データの件数
//        putRequestScope(AttributeConst.PAGE, page); //ページ数
//        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数
//
//        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、
//        //セッションからは削除する
//        String flush = getSessionScope(AttributeConst.FLUSH);
//        if (flush != null) {
//            putRequestScope(AttributeConst.FLUSH, flush);
//            removeSessionScope(AttributeConst.FLUSH);
//        }
//
//        //一覧画面を表示
//        forward(ForwardConst.FW_SHI_INDEX);
//    }
//
//    //↑ここまでが()index

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

        //shift情報の空インスタンスに、shiftの日付＝今日の日付を設定する
        ShiftView shv = new ShiftView();
        shv.setInputAt(LocalDateTime.now());
        putRequestScope(AttributeConst.SHIFT, shv); //日付のみ設定済みのshiftインスタンス

        //新規登録画面を表示
        forward(ForwardConst.FW_SHI_NEW);

    }

    /**
     * DBに新規登録を行うメソッド
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //日報の日付が入力されていなければ、今日の日付を設定
            LocalDateTime datetime = null;
            if (getRequestParam(AttributeConst.SHI_INPUT_AT) == null
                    || getRequestParam(AttributeConst.SHI_INPUT_AT).equals("")) {
                datetime = LocalDateTime.now();
            } else {
                datetime = LocalDateTime.parse(getRequestParam(AttributeConst.SHI_INPUT_AT));
            }

            //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

            //パラメータの値をもとにShift情報のインスタンスを作成する
            ShiftView shv = new ShiftView(
                    null,
                    ev, //ログインしている従業員を、作成者として登録する
                    getRequestParam(AttributeConst.SHI_INOROUT),
                    datetime);

            //Shift情報登録
            List<String> errors = service.create(shv);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.SHIFT, shv);//入力された日報情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_SHI_NEW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_INOROUT.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);

            }
        }
    }
}
