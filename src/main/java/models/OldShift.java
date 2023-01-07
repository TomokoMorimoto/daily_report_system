package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 日報データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_SFT)

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class OldShift {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.SFT_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 出退勤を登録した従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.SFT_COL_EMP, nullable = false)
    private Employee employee;

    /**
     * 出勤登録日時
     */
    @Column(name = JpaConst.SFT_COL_BEGIN_ATTIME, nullable = false)
    private LocalDateTime beginAt;

    /**
     * 退勤日時
     */
    @Column(name = JpaConst.SFT_COL_FINISH_ATTIME, nullable = true)
    private LocalDateTime finishAt;

}