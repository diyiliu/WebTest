import com.jhlabs.image.CropFilter;
import com.jhlabs.image.ScaleFilter;
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
    public void test2() throws Exception {
        String path = "C:\\Users\\DIYILIU\\Desktop\\images\\picture.jpg";
        String path1 = "C:\\Users\\DIYILIU\\Desktop\\images\\picture1.jpg";
        String path2 = "C:\\Users\\DIYILIU\\Desktop\\images\\picture2.jpg";


        BufferedImage fromImage = ImageIO.read(new File(path));
        //
        BufferedImage toImage = new BufferedImage(576, 576, BufferedImage.TYPE_INT_RGB);
        CropFilter cropFilter = new CropFilter(352, 72, 576, 576);
        cropFilter.filter(fromImage, toImage);

//        toImage = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
//        ScaleFilter scaleFilter = new ScaleFilter(128, 128);
//        scaleFilter.filter(fromImage, toImage);
        // 写回指定目标文件
        ImageIO.write(toImage, "jpg", new File(path2));
    }
}
