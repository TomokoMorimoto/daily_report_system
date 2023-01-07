package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Shift;

/**
 * shift1データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class ShiftConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param shv Shift1Viewのインスタンス
     * @return Shift1のインスタンス
     */
    public static Shift toModel(ShiftView shv) {
        return new Shift(
                shv.getId(),
                EmployeeConverter.toModel(shv.getEmployee()),
                shv.getInorout(),
                shv.getInputAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param sht Shiftのインスタンス
     * @return Shift1Viewのインスタンス
     */
    public static ShiftView toView(Shift sht) {

        if (sht == null) {
            return null;
        }

        return new ShiftView(
                sht.getId(),
                EmployeeConverter.toView(sht.getEmployee()),
                sht.getInorout(),
                sht.getInputAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<ShiftView> toViewList(List<Shift> list) {
        List<ShiftView> evs = new ArrayList<>();

        for (Shift sht : list) {
            evs.add(toView(sht));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param s1 DTOモデル(コピー先)
     * @param shv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Shift sht, ShiftView shv) {
        sht.setId(shv.getId());
        sht.setEmployee(EmployeeConverter.toModel(shv.getEmployee()));
        sht.setInorout(shv.getInorout());
        sht.setInputAt(shv.getInputAt());
    }

}