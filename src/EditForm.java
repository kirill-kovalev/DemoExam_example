/*
created by author <student@exam.com>
Окно добавления и редактирования данных
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditForm extends JFrame{
    private JTextField id;
    private JTextField title;
    private JTextField duration;
    private JTextField cost;
    private JTextField description;
    private JTextField discount;
    private JTextField imagePath;
    private JButton exitBtn;
    private JButton okBtn;
    private JLabel idLabel;
    private JPanel root;

    EditForm(DBStruct.service item){
        setContentPane(root);
        setSize(500,500);
        show();

        if (item == null){
            setTitle("Добавление");
            id.hide();
            idLabel.hide();
            okBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new Database().sql("INSERT INTO `student`.`service`\n" +
                                "(`Title`,              `Cost`,           `DurationInSeconds`,  `Description`,             `Discount`,              `MainImagePath`) VALUES\n" +
                                "('"+title.getText()+"',"+cost.getText()+","+duration.getText()+",'"+description.getText()+"',"+discount.getText()+",'"+imagePath.getText()+"');");
                        dispose();
                    } catch (SQLException throwables) {
                        JOptionPane.showMessageDialog(null,"Ошибка добавления \n Проверьте корректность заполнения полей!","Ошибка!",JOptionPane.ERROR_MESSAGE);
                        throwables.printStackTrace();
                    }
                }
            });
        }else{
            setTitle("Редактирование");
            id.setText(item.id+"");
            title.setText(item.Title+"");
            duration.setText(item.DurationInSeconds+"");
            cost.setText(item.Cost+"");
            description.setText(item.Description+"");
            discount.setText(item.Discount+"");
            imagePath.setText(item.MainImagePath+"");
            okBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new Database().sql("UPDATE `student`.`service`\n" +
                                "SET " +
                                "`Title` = '"+title.getText()+"' , " +
                                "`Cost` = "+cost.getText()+" , " +
                                "`DurationInSeconds` = "+duration.getText()+" , " +
                                "`Description` = '"+description.getText()+"', " +
                                "`Discount` =  "+discount.getText()+", " +
                                "`MainImagePath` = '"+imagePath.getText()+"'  " +
                                "WHERE `ID` = "+id.getText()+" ;");
                        dispose();
                    } catch (SQLException throwables) {
                        JOptionPane.showMessageDialog(null,"Ошибка добавления \n Проверьте корректность заполнения полей!","Ошибка!",JOptionPane.ERROR_MESSAGE);
                        throwables.printStackTrace();
                    }
                }
            });
        }

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
}
