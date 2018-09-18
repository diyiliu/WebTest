import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Description: TestImg
 * Author: DIYILIU
 * Update: 2018-09-17 11:39
 */
public class TestImg {

    @Test
    public void test() throws Exception {
        String path = "C:\\Users\\DIYILIU\\Desktop\\images\\picture.jpg";
        String path1 = "C:\\Users\\DIYILIU\\Desktop\\images\\picture1.jpg";

        BufferedImage img = ImageIO.read(new File(path));
        img = img.getSubimage(352, 72, 512, 512);
        ImageIO.write(img, "jpg", new File(path1));
    }

    @Test
    public void test1() throws Exception {
        String path = "C:\\Users\\DIYILIU\\Desktop\\images\\picture.jpg";
        String path1 = "C:\\Users\\DIYILIU\\Desktop\\images\\picture1.jpg";

        IJ.open(path);
        IJ.run("Size...", "width=400 height=300 constrain average interpolation=Bilinear");
        IJ.save(path1);
    }


    /**
     * 重置图片大小
     *
     * @throws Exception
     */
    @Test
    public void test11() throws Exception {
        String path = "C:\\Users\\DIYILIU\\Desktop\\images\\picture.jpg";
        String path1 = "C:\\Users\\DIYILIU\\Desktop\\images\\picture1.jpg";

        ImagePlus imp = IJ.openImage(path);
        ImageProcessor processor = imp.getProcessor().resize(218, 218);
        imp.setProcessor(processor);

        IJ.save(imp, path1);
    }

    /**
     * 剪切图片
     *
     * @throws Exception
     */
    @Test
    public void test111() throws Exception {
        String path = "C:\\Users\\DIYILIU\\Desktop\\images\\picture.jpg";
        String path1 = "C:\\Users\\DIYILIU\\Desktop\\images\\picture1.jpg";

        ImagePlus imp = IJ.openImage(path);
        imp.setRoi(500, 0, 516, 516);
        imp = imp.duplicate();

        IJ.save(imp, path1);
    }



    @Test
    public void test2() throws Exception {
        String path = "C:\\Users\\DIYILIU\\Desktop\\images\\picture.jpg";
        String path2 = "C:\\Users\\DIYILIU\\Desktop\\images\\picture2.jpg";

        ImagePlus imp = IJ.openImage(path);
        imp.setRoi(500, 0, 516, 516);
        imp = imp.duplicate();

        ImageProcessor processor = imp.getProcessor().resize(128, 128);
        imp.setProcessor(processor);
        IJ.save(imp, path2);
    }
}
