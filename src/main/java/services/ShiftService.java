package services;

import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.ShiftConverter;
import actions.views.ShiftView;
import constants.JpaConst;
import models.Shift;
import models.validators.ShiftValidator;

/**
 * shiftテーブルの操作に関わる処理を行うクラス
 */
public class ShiftService extends ServiceBase {

        /**
         * 指定した従業員が作成したShiftデータを、指定されたページ数の一覧画面に表示する分取得し
         * ShiftViewのリストで返却する
         * @param employee 従業員
         * @param page ページ数
         * @return 一覧画面に表示するデータのリスト
         */
        public List<ShiftView> getMinePerPage(EmployeeView employee, int page) {

            List<Shift> shifts = em.createNamedQuery(JpaConst.Q_SHI_GET_ALL_MINE, Shift.class)
                    .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                    .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                    .setMaxResults(JpaConst.ROW_PER_PAGE)
                    .getResultList();
            return ShiftConverter.toViewList(shifts);
        }

        /**
         * 指定した従業員が作成したShiftデータの件数を取得し、返却する
         * @param employee
         * @return Shiftデータの件数
         */
        public long countAllMine(EmployeeView employee) {

            long count = (long) em.createNamedQuery(JpaConst.Q_SHI_COUNT_ALL_MINE, Long.class)
                    .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                    .getSingleResult();

            return count;
        }

        /**
         * 指定されたページ数の一覧画面に表示するShiftデータを取得し、ShiftViewのリストで返却する
         * @param page ページ数
         * @return 一覧画面に表示するデータのリスト
         */
        public List<ShiftView> getAllPerPage(int page) {

            List<Shift> shifts = em.createNamedQuery(JpaConst.Q_SHI_GET_ALL, Shift.class)
                    .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                    .setMaxResults(JpaConst.ROW_PER_PAGE)
                    .getResultList();
            return ShiftConverter.toViewList(shifts);
        }

        /**
         * 日報テーブルのデータの件数を取得し、返却する
         * @return データの件数
         */
        public long countAll() {
            long shifts_count = (long) em.createNamedQuery(JpaConst.Q_SHI_COUNT, Long.class)
                    .getSingleResult();
            return shifts_count;
        }

        /**
         * idを条件に取得したデータをShiftViewのインスタンスで返却する
         * @param id
         * @return 取得データのインスタンス
         */
        public ShiftView findOne(int id) {
            return ShiftConverter.toView(findOneInternal(id));
        }

    /**
     * 画面から入力された日報の登録内容を元にデータを1件作成し、日報テーブルに登録する
     * @param shv 日報の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(ShiftView shv) {
        List<String> errors = ShiftValidator.validate(shv); //
        if (errors.size() == 0) { //エラーのサイズ（数もしくは）が0だったら
            //LocalDateTime ldt = LocalDateTime.now();//日時　＝　日時は今（今の日時）
            //shv.setInputAt(ldt); //shvの、InputAtにセットします。Ldtを（LdtをSHVのIAにセットします）
            createInternal(shv); //shvをInternalcreateします
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }


    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Shift findOneInternal(int employee_id) {
        return em.find(Shift.class, employee_id);
    }

    /**
     * Shiftデータを1件登録する
     * @param shv 日報データ
     */
    private void createInternal(ShiftView shv) {

        em.getTransaction().begin();
        em.persist(ShiftConverter.toModel(shv));
        em.getTransaction().commit();

    }

}
