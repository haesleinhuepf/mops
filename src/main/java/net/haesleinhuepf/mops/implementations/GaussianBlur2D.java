package net.haesleinhuepf.mops.implementations;

import ij.IJ;
import net.imagej.ImageJ;
import net.haesleinhuepf.clij.clearcl.ClearCLBuffer;
import net.haesleinhuepf.clij.macro.AbstractCLIJPlugin;
import net.haesleinhuepf.clij.macro.CLIJMacroPlugin;
import net.haesleinhuepf.clij.macro.CLIJOpenCLProcessor;
import net.haesleinhuepf.clij.macro.documentation.OffersDocumentation;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.img.display.imagej.ImageJFunctions;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import net.haesleinhuepf.mops.MopsBase;

/**
 * The GaussianBlur2D
 *
 * Author: @haesleinhuepf
 * 6 2019
 */
@Plugin(type = CLIJMacroPlugin.class, name = "MOPS_gaussianBlur2D")
public class GaussianBlur2D extends MopsBase implements OffersDocumentation {

    @Override
    public RandomAccessibleInterval run() {
        RandomAccessibleInterval result = (Img)ops.filter().gauss(asImg(args[0]), asFloat(args[1]), asFloat(args[2]));
        ImageJFunctions.show(result);
        IJ.getImage().setTitle(this.getClass().getSimpleName());
        return result;
    }

    @Override
    public String getParameters() {
        return "Image image, Number sigmaX, Number sigmaY";
    }

    @Override
    public String getDescription() {
        return "Apply a filter to an image with a given sigma parameter.";
    }

    @Override
    public String getAvailableForDimensions() {
        return "2D, 3D";
    }
}
