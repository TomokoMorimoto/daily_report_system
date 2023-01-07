package actions;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;

import actions.views.ShiftView;
import constants.AttributeConst;
import constants.ForwardConst;
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
    //        //指定されたページ数の一覧画面に表示する日報データを取得
    //        int page = getPage();
    //        List<ReportView> reports = service.getAllPerPage(page);
    //
    //        //全日報データの件数を取得
    //        long reportsCount = service.countAll();
    //
    //        putRequestScope(AttributeConst.REPORTS, reports); //取得した日報データ
    //        putRequestScope(AttributeConst.REP_COUNT, reportsCount); //全ての日報データの件数
    //        putRequestScope(AttributeConst.PAGE, page); //ページ数
    //        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数
    //
    //        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
    //        String flush = getSessionScope(AttributeConst.FLUSH);
    //        if (flush != null) {
    //            putRequestScope(AttributeConst.FLUSH, flush);
    //            removeSessionScope(AttributeConst.FLUSH);
    //        }
    //
    //        //一覧画面を表示
    //        forward(ForwardConst.FW_REP_INDEX);
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
    putRequestScope(AttributeConst.REPORT, shv); //日付のみ設定済みのshiftインスタンス

    //新規登録画面を表示
    forward(ForwardConst.FW_SHI_NEW1);

}
}
