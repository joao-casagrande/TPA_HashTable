import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ChooseFile {
    private JFrame frame;
    public ChooseFile() {
        frame = new JFrame();

        frame.setVisible(true);
        BringToFront();
    }
    public File GetFile() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Arquivos .csv", "csv");

        fc.setFileFilter(filter);
        if(JFileChooser.APPROVE_OPTION == fc.showOpenDialog(null)){
            frame.setVisible(false);
            return fc.getSelectedFile();
        }else {
            System.out.println("Next time select a file.");
            System.exit(1);
        }
        return null;
    }

    private void BringToFront() {
        frame.setExtendedState(JFrame.ICONIFIED);
        frame.setExtendedState(JFrame.NORMAL);

    }

}