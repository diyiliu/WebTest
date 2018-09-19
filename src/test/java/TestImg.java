import com.madgag.gif.fmsware.AnimatedGifEncoder;
import com.madgag.gif.fmsware.GifDecoder;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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


    @Test
    public void test3() throws Exception {
        String path = "C:\\Users\\DIYILIU\\Desktop\\images\\2.gif";
        String path2 = "C:\\Users\\DIYILIU\\Desktop\\images\\22.gif";

        ImagePlus imp = IJ.openImage(path);
//        impB.setStack(impB.getStack().crop(50, 50, 0, 128, 128, impB.getStack().getSize()));//Crop


    }

    @Test
    public void test4() throws Exception {
        String path = "C:\\Users\\DIYILIU\\Desktop\\images\\picture.jpg";
        String path2 = "C:\\Users\\DIYILIU\\Desktop\\images\\picture22.jpg";

        ImagePlus impB = IJ.openImage(path);
        impB.setStack(impB.getStack().crop(500, 0, 0, 516, 516, impB.getStack().getSize()));//Crop

        //impB.getProcessor().scale(0.5, 0.5);
        ImageProcessor ip = impB.getProcessor().resize(128, 128);
        impB.setProcessor(ip);

        IJ.save(impB, path2);
    }

    @Test
    public void test5() throws Exception {
        String path = "C:\\Users\\DIYILIU\\Desktop\\images\\2.gif";
        String path2 = "C:\\Users\\DIYILIU\\Desktop\\images\\22.gif";

        zoomGifBySize(path, 100, 100, path2);
    }


    public void zoomGifBySize(String imagePath, int width, int height, String outputPath) throws IOException {
        // GIF需要特殊处理
        GifDecoder decoder = new GifDecoder();
        int status = decoder.read(imagePath);
        if (status != GifDecoder.STATUS_OK) {
            throw new IOException("read image " + imagePath + " error!");
        }
        // 拆分一帧一帧的压缩之后合成
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outputPath);
        encoder.setRepeat(decoder.getLoopCount());
        for (int i = 0; i < decoder.getFrameCount(); i++) {
            encoder.setDelay(decoder.getDelay(i));// 设置播放延迟时间
            BufferedImage bufferedImage = decoder.getFrame(i);// 获取每帧BufferedImage流
            BufferedImage zoomImage = new BufferedImage(width, height, bufferedImage.getType());
            Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            Graphics gc = zoomImage.getGraphics();
            gc.setColor(Color.WHITE);
            gc.drawImage(image, 0, 0, null);
            encoder.addFrame(zoomImage);
        }
        encoder.finish();
        File outFile = new File(outputPath);
        BufferedImage image = ImageIO.read(outFile);
        ImageIO.write(image, outFile.getName(), outFile);
    }


    @Test
    public void test6() throws Exception {
        String path = "C:\\Users\\DIYILIU\\Desktop\\images\\2.gif";
        String path1 = "C:\\Users\\DIYILIU\\Desktop\\images\\22.gif";
        String path2 = "C:\\Users\\DIYILIU\\Desktop\\images\\222.gif";

        cropGifBySize(path, 150, 50, 120, 100, path1);
        zoomGifBySize(path1, 60, 50, path2);
    }


    public void cropGifBySize(String imagePath, int x, int y, int width, int height, String outputPath) throws IOException {
        // GIF需要特殊处理
        GifDecoder decoder = new GifDecoder();
        int status = decoder.read(imagePath);
        if (status != GifDecoder.STATUS_OK) {
            throw new IOException("read image " + imagePath + " error!");
        }
        // 拆分一帧一帧的压缩之后合成
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outputPath);
        encoder.setRepeat(decoder.getLoopCount());
        for (int i = 0; i < decoder.getFrameCount(); i++) {
            encoder.setDelay(decoder.getDelay(i));// 设置播放延迟时间
            BufferedImage bufferedImage = decoder.getFrame(i);// 获取每帧BufferedImage流
            BufferedImage zoomImage = bufferedImage.getSubimage(x, y, width, height);

            Graphics gc = zoomImage.getGraphics();
            gc.setColor(Color.WHITE);
            encoder.addFrame(zoomImage);
        }
        encoder.finish();
        File outFile = new File(outputPath);
        BufferedImage image = ImageIO.read(outFile);
        ImageIO.write(image, outFile.getName(), outFile);
    }
}
