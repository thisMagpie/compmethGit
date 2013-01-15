/*
* ********************************************************************************* *
* ********************* By Magdalen Berns for miniproject 2012 ******************** *
* ********************************************************************************* *

*   CONTAINS:

*   a static method to compute the gaussian distribution
*
*   a static method to convolve two signals (expected input gaussian distribution and set of data points we wish to smooth

*   Static methods to calculate the mean and standard deviation (and square of std dev) of an integer array of data points.
*   */
class StatsUtil {

    private static final double period = 2 * Math.PI;

    //Arguments: sample number, mean and std deviation
    public static double[] gaussian(int sampleNumber, double sigmaSquared, double mean){

        // Create Gaussian
        double[] gaussian = new double[sampleNumber];
        double tempGaussian= 0.0;
        for (int i=0; i<sampleNumber; i++){
            gaussian[i] = Math.sqrt(1/(period)*sigmaSquared)*(Math.exp(-(i-mean)*(i-mean)/(2*sigmaSquared)));
            tempGaussian += gaussian[i];
        }
        //Normalize the data array
        for (int i=0; i<sampleNumber; i++){
            gaussian[i] /= tempGaussian;
        }
        return gaussian;
    }

    public static double[] convolve(int[] data, double[] gaussian, int sampleNumber){

        // The convolution smoothing.
        double convolved[] = new double[data.length - (sampleNumber + 1)];
        for (int i=0; i<convolved.length; i++){
            convolved[i] = 0.0;  // Set all doubles to 0.
            for (int j=i, k=0; j<i+sampleNumber; j++, k++){
                convolved[i] +=  data[j] * gaussian[k];
            }
        }
        return convolved;
    }
    public static double mean(int[] data){
        double sum =0;
        for(int  i = 0; i<data.length;i++)sum += data[i];
         return sum/data.length;
    }
    public static double mean(double[] data){
        double sum =0;
        for(int  i = 0; i<data.length;i++)sum += data[i];
         return sum/data.length;
    }
    public static double sum(double data){
        double sum =0;
        sum += data;
        return sum;
    }

    //std deviation   squared
    public static double devSq(int[] data, double mean){
        double tot=0;
        for(int i = 0; i < data.length; i++)
        tot+= Math.pow((data[i] - mean),2);
        return tot;
    }

    //std deviation   squared
    public static double devSq(double[] data, double mean){
        double tot=0;
        for(int i = 0; i < data.length; i++)
        tot+= Math.pow((data[i] - mean),2);
        return tot;
    }

    //standard
    public static double dev(double stdDev, int dataPoints){
        return Math.sqrt(stdDev)/(dataPoints-1);
    }
    public static int findSampleNo(double largest, double[] data){
        int sample=0;
        for(int i=1; i<data.length; i++){
            if(data[i]==largest){
                sample=i;
            }
        }
        return sample;
    }
    public static double findPeak(double[] data){
        double  largest=data[0];
        for(int i=0; i<data.length; i++){
            if(data[i]>largest){
                largest = data[i];
            }
        }
        return largest;
    }

}
