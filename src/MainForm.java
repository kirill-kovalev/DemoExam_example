/*
created by author <student@exam.com>
Главное окно приложения
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainForm extends JFrame {
    private JTable contentTable;
    private JPanel root;
    private JButton refreshBtn;
    private JButton removeBtn;
    private JButton editBtn;
    private JButton addBtn;
    private JComboBox discountComboBox;
    ArrayList<DBStruct.service> serviceList;

    public static void main(String arg[]){
        MainForm startForm = new MainForm();
    }
    MainForm(){
        setContentPane(root);
        setTitle("Услуги");
        show();
        setVisible(true);
        setSize(500,500);
        serviceList = loadSevices();
        updateTable(serviceList);

        refreshBtn.addActionListener(e -> {
            serviceList = loadSevices();
            updateTable(serviceList);
        });
        editBtn.addActionListener(e->{
            int index = contentTable.getSelectedRow() - 1;
            if (index >=0){
                new EditForm(serviceList.get(index));
            }
            serviceList = loadSevices();
            updateTable(serviceList);
        });
        addBtn.addActionListener(e -> {

            new EditForm(null);

            serviceList = loadSevices();
            updateTable(serviceList);
        });


        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addElement("Все");
        comboBoxModel.addElement("0-30");
        comboBoxModel.addElement("30-70");
        comboBoxModel.addElement("70-100");
        discountComboBox.setModel(comboBoxModel);

        discountComboBox.addActionListener(e -> {
            int borders[] = new int[]{0,100};
            ArrayList<DBStruct.service> filtered = new ArrayList<>();
            switch (discountComboBox.getSelectedIndex()){
                case 0:
                    borders = new int[]{0,100};
                    break;
                case 1:
                    borders = new int[]{0,30};
                    break;
                case 2:
                    borders = new int[]{30,70};
                    break;
                case 3:
                    borders = new int[]{70,100};
                    break;
            }
            for (DBStruct.service item : serviceList){
                if (item.Discount>borders[0] && item.Discount<= borders[1]){
                    filtered.add(item);
                }
            }
            serviceList = filtered;
            updateTable(serviceList);
        });


    }

    ArrayList<DBStruct.service> loadSevices(){
        ArrayList<DBStruct.service> services = new ArrayList<>();
        ResultSet rs = new Database().query("Select * from service");
        try {
            while (rs.next()){
                services.add(new DBStruct.service(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  services;
    }
    void updateTable(ArrayList<DBStruct.service> services){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Наименование");
        tableModel.addColumn("Стоимость");
        tableModel.addColumn("Стоимость с учетом скидки");
        tableModel.addColumn("Скидка");
        tableModel.addColumn("Продолжительность работ в минутах");
        tableModel.addRow(new String[]{"Наименование","Стоимость","Стоимость с учетом скидки","Скидка","Продолжительность работ в минутах"});
        for (DBStruct.service item : services){
            Double priceWithDiscount =  item.Cost*(1 - item.Discount/100);
            tableModel.addRow(new String[]{item.Title,item.Cost+"",priceWithDiscount.intValue()+"",item.Discount*100+"%", new Integer(item.DurationInSeconds/60).intValue() +""});
        }
        contentTable.setModel(tableModel);
    }

}
