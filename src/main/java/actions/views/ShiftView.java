package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * shift1情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class ShiftView {

    /**
     * id
     */
    private Integer id;

    /**
     * shiftを登録した従業員
     */
    private EmployeeView employee;

    /**
     * 出勤／退勤
     */
    private String inorout;

    /**
     * 出退勤日時
     */
    private LocalDateTime inputAt;

}