package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ShiftView;
import constants.MessageConst;

/**
 * shiftインスタンスに設定されている値のバリデーションを行うクラス
 */
public class ShiftValidator {

    /**
     * shiftインスタンスの各項目についてバリデーションを行う
     * @param shv 日報インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(ShiftView shv) {
        List<String> errors = new ArrayList<String>();

        //出退勤のチェック
        String inoroutError = validateInorout(shv.getInorout());
        if (!inoroutError.equals("")) {
            errors.add(inoroutError);

        }

        return errors;
    }

    /**
     * タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param inorout 出退勤
     * @return エラーメッセージ
     */
    private static String validateInorout(String inorout) {
        if (inorout == null || inorout.equals("")) {
            return MessageConst. E_NOTINOROUT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

}
