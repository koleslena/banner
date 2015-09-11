package ru.koleslena.demo.generate

import javax.imageio.ImageIO
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage

/**
 * Created by elenko on 24.06.15.
 */
class SqlGenerator {

    static private PrintStream getOutput (String target) {
        File targetFile = new File(target)
        File parentDir = targetFile.parentFile;

        if (!parentDir.directory && !parentDir.mkdirs()) {
            throw new Exception("unable to create directory ${parentDir.absolutePath}")
        }

        FileOutputStream out = new FileOutputStream(targetFile);
        PrintStream fileWriter = new PrintStream(true, out);
        fileWriter
    }

    public static void main(String[] args) {
        PrintStream pout = getOutput("src/main/resources/sql/dbSeed.sql")
        seedDb(pout)
        //seedDb(System.out)
    }

    static void seedDb(PrintStream out) {
        for(int i = 1; i < 100; i++) {
            out.print("""
                        insert into banner (id, content, name, ref) values (${i+1000000}, X'01${getImage(i).encodeHex().toString()}', 'name $i', 'www.ya.ru');
                        insert into clicks (bannerid, counting) values (${i+1000000}, ${100-i});
                        insert into shows (bannerid, counting) values (${i+1000000}, ${100+i});""");
        }
    }

    static byte[] getImage(int i) {
        int width = 200;
        int height = 200;
        BufferedImage off_Image =
                new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = off_Image.createGraphics();

        g2.setColor(new Color(i, i, i));
        g2.drawString("str $i$i$i", 0, 0);

        g2.dispose();

	    ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ImageIO.write( off_Image, "jpg", baos );
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        imageInByte
    }
}
