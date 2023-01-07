package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.OldShiftConverter;
import actions.views.OldShiftView;
import constants.JpaConst;
import models.OldShift;

/**
 * shiftテーブルの操作に関わる処理を行うクラス
 */
public class OldShiftService extends ServiceBase {

    //    /**
    //     * 指定した従業員が作成したshiftデータを、指定されたページ数の一覧画面に表示する分取得しShiftViewのリストで返却する
    //     * @param employee 従業員
    //     * @param page ページ数
    //     * @return 一覧画面に表示するデータのリスト
    //     */
    //    public List<ShiftView> getMinePerPage(EmployeeView employee, int page) {
    //
    //        List<Shift> shifts = em.createNamedQuery(JpaConst.Q_REP_GET_ALL_MINE, Shift.class)
    //                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
    //                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
    //                .setMaxResults(JpaConst.ROW_PER_PAGE)
    //                .getResultList();
    //        return ShiftConverter.toViewList(shifts);
    //    }

    /**
     * 指定した従業員が作成したShiftデータの件数を取得し、返却する
     * @param employee
     * @return Shiftデータの件数
     */
    public long countAllMine(EmployeeView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_REP_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }

    //    /**
    //     * 指定されたページ数の一覧画面に表示するShiftデータを取得し、ShiftViewのリストで返却する
    //     * @param page ページ数
    //     * @return 一覧画面に表示するデータのリスト
    //     */
    //    public List<ShiftView> getAllPerPage(int page) {
    //
    //        List<Shift> shifts = em.createNamedQuery(JpaConst.Q_REP_GET_ALL, Shift.class)
    //                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
    //                .setMaxResults(JpaConst.ROW_PER_PAGE)
    //                .getResultList();
    //        return ShiftConverter.toViewList(shifts);
    //    }

    /**
     * Shiftテーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long shifts_count = (long) em.createNamedQuery(JpaConst.Q_REP_COUNT, Long.class)
                .getSingleResult();
        return shifts_count;
    }

    /**
     * idを条件に取得したデータをShiftViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public OldShiftView findOne(EmployeeView employee_id) { //1件データを取ってくる
        return OldShiftConverter.toView(findOneInternal(employee_id));
    }

    /**
     * 画面から入力されたShiftの登録内容を元にデータを1件作成し、日報テーブルに登録する
     * @param sv 日報の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(OldShiftView sv) {
        LocalDateTime ldt = LocalDateTime.now();
        sv.setBegin_at(ldt);
        sv.setFinish_at(ldt);
        createInternal(sv);
        return null; //TODO！！！！！！！！！！仮！！！！！！！！！！！！！！！！
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private OldShift findOneInternal(EmployeeView empoyee_id) {//TODO 何を条件にデータを取ってくるか（em_idと今日の日付出勤）
        return em.find(OldShift.class, empoyee_id);
    }

    /**
     * Shiftデータを1件登録する
     * @param sv Shiftデータ
     */
    private void createInternal(OldShiftView sv) {

        em.getTransaction().begin();
        em.persist(OldShiftConverter.toModel(sv));
        em.getTransaction().commit();

    }

}