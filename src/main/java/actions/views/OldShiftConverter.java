package actions.views;

import models.OldShift;

/**
 * 出退勤データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class OldShiftConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param ev ShiftViewのインスタンス
     * @return Shiftのインスタンス
     */
    public static OldShift toModel(OldShiftView sv) {
        return new OldShift(
                sv.getId(),
                EmployeeConverter.toModel(sv.getEmployee_id()),
                sv.getBegin_at(),
                sv.getFinish_at());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param s ShiftViewのインスタンス
     * @return ShiftViewのインスタンス
     */
    public static OldShiftView toView(OldShift s) {

        if (s == null) {
            return null;
        }

        return new OldShiftView(
                s.getId(),
                EmployeeConverter.toView(s.getEmployee()),
                s.getBeginAt(),
                s.getFinishAt());
    }

    //    /**
    //     * DTOモデルのリストからViewモデルのリストを作成する
    //     * @param list DTOモデルのリスト
    //     * @return Viewモデルのリスト
    //     */
    //    public static List<ShiftView> toViewList(List<Employee> list) {
    //        List<EmployeeView> evs = new ArrayList<>();
    //
    //        for (Employee e : list) {
    //            evs.add(toView(e));
    //        }
    //
    //        return evs;
    //    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(OldShift s, OldShiftView sv) {
        s.setId(sv.getId());
        s.setEmployee(EmployeeConverter.toModel(sv.getEmployee_id()));
        sv.setBegin_at(sv.getBegin_at());
        sv.setFinish_at(sv.getFinish_at());
    }
}