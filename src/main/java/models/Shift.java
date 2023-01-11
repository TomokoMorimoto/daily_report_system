package models;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * shiftデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_SHI)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_SHI_GET_ALL,
            query = JpaConst.Q_SHI_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_SHI_COUNT,
            query = JpaConst.Q_SHI_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_SHI_GET_ALL_MINE,
            query = JpaConst.Q_SHI_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_SHI_COUNT_ALL_MINE,
            query = JpaConst.Q_SHI_COUNT_ALL_MINE_DEF)
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Shift {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.SHI_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * shiftを登録した従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.SHI_COL_EMP, nullable = false)
    private Employee employee;

    /**
     * 出勤／退勤
     */
    @Column(name = JpaConst.SHI_COL_SHIFT, nullable = false)
    private String inorout;

    /**
     * 出退勤を投稿した日時
     */
    @Column(name = JpaConst.SHI_COL_INPUT_AT, nullable = false)
    private LocalDateTime inputAt;


}
