package miscs;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class R {
    public static R anchor = new R(); // Never change !!! Never Move!!!

    public enum Rs {

//        GConsoleGUI_messages_html("GConsoleGUI", "messages.html"), השורה הזאת בשינוי קטן מאפשרת לטעון ספריות שלמות

        _Email_001("", "Email_001.html"),
        Agam_Logo("", "Agam-Logo.png"),
        ;


        private TwoWayFile twoWayFile;
        private final String dir;
        private final String file;

        Rs(String dir, String file) {
            this.dir = dir;
            this.file = file;
            this.twoWayFile = get2WayFile();

        }

        public TwoWayFile get2WayFile() {
            if (this.twoWayFile != null)
                return this.twoWayFile;

            TwoWayFile twoWayFile=new TwoWayFile();


            File processId_Dir = new File(Globals.VIRTUAL_FOLDER + Globals.PROCESS_ID + "\\");

            if (!processId_Dir.exists()) {
                processId_Dir.deleteOnExit();
                processId_Dir.mkdir();
            }

            File actualFile = null;

            if (!(this.dir == null || this.dir.equals(""))) {
                File dir2 = new File(Globals.VIRTUAL_FOLDER + Globals.PROCESS_ID + "\\" + this.dir);

                if (!dir2.exists()) {
                    dir2.deleteOnExit();
                    dir2.mkdir();
                }

                actualFile = new File(Globals.VIRTUAL_FOLDER + Globals.PROCESS_ID + "\\" + this.dir + "\\" + this.file);
            } else
                actualFile = new File(Globals.VIRTUAL_FOLDER + Globals.PROCESS_ID + "\\" + this.file);



            actualFile.deleteOnExit();
            writeByteArray(actualFile, R.anchor.getResourcesBytes(this.dir, this.file));

            twoWayFile.file = actualFile;
            twoWayFile.localPath = convertFilePathToLocalPath(actualFile);

            return twoWayFile;
        }

        private static void writeByteArray(File f, byte[] bArr) {

            try {

                f.createNewFile();

                FileOutputStream fos = new FileOutputStream(f);

                fos.write(bArr);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();//////////////////
            }

        }
    }


    private R() {

    }

    public static void loadWholeDir(String dirName) {
        final Rs[] values = Rs.values();

        for (Rs value : values)
            if (value.name().startsWith(dirName))
                value.get2WayFile();

    }

    private byte[] getResourcesBytes(String dirName, String fileName) {
        InputStream is;
        if (dirName == null || dirName.equals(""))
            is = getClass().getResourceAsStream("/" + fileName);
        else
            is = getClass().getResourceAsStream("/" + dirName + "/" + fileName);

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            byte[] data = new byte[4];

            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();

            return buffer.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public byte[] getResourcesBytes(Rs rs) {
        return getResourcesBytes(rs.dir, rs.file);
    }

    private static String convertFilePathToLocalPath(File file) {
        String f = file.getAbsolutePath();

        f = f.replace("\\", "/");

        f = "file:///" + f;
        return f;
    }

    public static class TwoWayFile {

        public File file;
        public String localPath;

    }
}
