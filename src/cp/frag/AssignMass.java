package cp.frag;

import java.util.ArrayList;
import java.util.List;

public class AssignMass {
    public static final int SIZE= 256;
    public static final float DIFFMASSC12C13 = 1.003354826f;

    public static final float MADD_DIFF_C12C13 = 1.003354826f;
    public static final int MADD_DIFF_C12C13_PPM = 1003;
    private static final float FLOAT_ZERO = 0.000001f;


    private static final float [] aaMassAvg = new float[SIZE];
    private static final float [] aaMassMono = new float[SIZE];
    public static final float NH3 = 17.02647f;
    public static final float H2O = 18.01051f;
    public static final float CO = 27.99491f;
    public static final float NH3_CS2 = 8.513235f;
    public static final float H2O_CS2 = 9.005255f;
    public static final float CO_CS2 = 13.997455f;

    public static final int NH3_INT = 17026;
    public static final int H2O_INT = 18011;
    public static final int CO_INT = 27995;
    public static final int NH3_CS2_INT = 8513;
    public static final int H2O_CS2_INT = 9005;
    public static final int CO_CS2_INT = 13998;


    private static float[] aaMasses;
    private static AssignMass assignMass;
    private static float cTerm;
    private static float nTerm;
    private static float xionfragment;
    private static float yionfragment;
    private static float zionfragment;
    private static float aionfragment;
    private static float bionfragment;
    private static float cionfragment;
    private static final double [][] isotopicDistribution = new double[20][20];
    public static final int[][] intensePeaks = new int[20][];
    public static final int[] mostIntensePeaks = new int[20];

    public static void addAAValue(char i, float value) {
        aaMassMono[i] += value;
    }

    public static void resetAA() {

        aaMassAvg['G'] =  57.05192f;   aaMassMono['G'] =  57.0214636f;
        aaMassAvg['A'] =  71.07880f;   aaMassMono['A'] =  71.0371136f;
        aaMassAvg['S'] =  87.07820f;   aaMassMono['S'] =  87.0320282f;
        aaMassAvg['P'] =  97.11668f;   aaMassMono['P'] =  97.0527636f;
        aaMassAvg['V'] =  99.13256f;   aaMassMono['V'] =  99.0684136f;
        aaMassAvg['T'] = 101.10508f;   aaMassMono['T'] = 101.0476782f;
        aaMassAvg['C'] = 103.13880f;   aaMassMono['C'] = 103.0091854f;
        aaMassAvg['L'] = 113.15944f;   aaMassMono['L'] = 113.0840636f;
        aaMassAvg['I'] = 113.15944f;   aaMassMono['I'] = 113.0840636f;
        aaMassAvg['X'] = 113.15944f;   aaMassMono['X'] = 113.0840636f;
        aaMassAvg['N'] = 114.10384f;   aaMassMono['N'] = 114.0429272f;
        aaMassAvg['O'] = 114.14720f;   aaMassMono['O'] = 114.0793126f;
        aaMassAvg['B'] = 114.59622f;   aaMassMono['B'] = 114.5349350f;
        aaMassAvg['D'] = 115.08860f;   aaMassMono['D'] = 115.0269428f;
        aaMassAvg['Q'] = 128.13072f;   aaMassMono['Q'] = 128.0585772f;
        aaMassAvg['K'] = 128.17408f;   aaMassMono['K'] = 128.0949626f;
        aaMassAvg['Z'] = 128.62310f;   aaMassMono['Z'] = 128.5505850f;
        aaMassAvg['E'] = 129.11548f;   aaMassMono['E'] = 129.0425928f;
        aaMassAvg['M'] = 131.19256f;   aaMassMono['M'] = 131.0404854f;
        aaMassAvg['H'] = 137.14108f;   aaMassMono['H'] = 137.0589116f;
        aaMassAvg['F'] = 147.17656f;   aaMassMono['F'] = 147.0684136f;
        aaMassAvg['R'] = 156.18748f;   aaMassMono['R'] = 156.1011106f;
        aaMassAvg['Y'] = 163.17596f;   aaMassMono['Y'] = 163.0633282f;
        aaMassAvg['W'] = 186.21320f;   aaMassMono['W'] = 186.0793126f;
    }

    static {
        aaMassAvg['G'] =  57.05192f;   aaMassMono['G'] =  57.0214636f;
        aaMassAvg['A'] =  71.07880f;   aaMassMono['A'] =  71.0371136f;
        aaMassAvg['S'] =  87.07820f;   aaMassMono['S'] =  87.0320282f;
        aaMassAvg['P'] =  97.11668f;   aaMassMono['P'] =  97.0527636f;
        aaMassAvg['V'] =  99.13256f;   aaMassMono['V'] =  99.0684136f;
        aaMassAvg['T'] = 101.10508f;   aaMassMono['T'] = 101.0476782f;
        aaMassAvg['C'] = 103.13880f;   aaMassMono['C'] = 103.0091854f;
        aaMassAvg['L'] = 113.15944f;   aaMassMono['L'] = 113.0840636f;
        aaMassAvg['I'] = 113.15944f;   aaMassMono['I'] = 113.0840636f;
        aaMassAvg['X'] = 113.15944f;   aaMassMono['X'] = 113.0840636f;
        aaMassAvg['N'] = 114.10384f;   aaMassMono['N'] = 114.0429272f;
        aaMassAvg['O'] = 114.14720f;   aaMassMono['O'] = 114.0793126f;
        aaMassAvg['B'] = 114.59622f;   aaMassMono['B'] = 114.5349350f;
        aaMassAvg['D'] = 115.08860f;   aaMassMono['D'] = 115.0269428f;
        aaMassAvg['Q'] = 128.13072f;   aaMassMono['Q'] = 128.0585772f;
        aaMassAvg['K'] = 128.17408f;   aaMassMono['K'] = 128.0949626f;
        aaMassAvg['Z'] = 128.62310f;   aaMassMono['Z'] = 128.5505850f;
        aaMassAvg['E'] = 129.11548f;   aaMassMono['E'] = 129.0425928f;
        aaMassAvg['M'] = 131.19256f;   aaMassMono['M'] = 131.0404854f;
        aaMassAvg['H'] = 137.14108f;   aaMassMono['H'] = 137.0589116f;
        aaMassAvg['F'] = 147.17656f;   aaMassMono['F'] = 147.0684136f;
        aaMassAvg['R'] = 156.18748f;   aaMassMono['R'] = 156.1011106f;
        aaMassAvg['Y'] = 163.17596f;   aaMassMono['Y'] = 163.0633282f;
        aaMassAvg['W'] = 186.21320f;   aaMassMono['W'] = 186.0793126f;
    }

    static {
        // from averagine
        isotopicDistribution[0][0] = 1.000000;
        isotopicDistribution[0][1] = 0.276950;
        isotopicDistribution[0][2] = 0.051217;
        isotopicDistribution[0][3] = 0.007109;
        isotopicDistribution[0][4] = 0.000000;
        isotopicDistribution[0][5] = 0.000000;
        isotopicDistribution[0][6] = 0.000000;
        isotopicDistribution[0][7] = 0.000000;
        isotopicDistribution[0][8] = 0.000000;
        isotopicDistribution[0][9] = 0.000000;
        isotopicDistribution[0][10] = 0.000000;
        isotopicDistribution[0][11] = 0.000000;
        isotopicDistribution[0][12] = 0.000000;
        isotopicDistribution[0][13] = 0.000000;
        isotopicDistribution[0][14] = 0.000000;
        isotopicDistribution[0][15] = 0.000000;
        isotopicDistribution[0][16] = 0.000000;
        isotopicDistribution[0][17] = 0.000000;
        isotopicDistribution[0][18] = 0.000000;
        isotopicDistribution[0][19] = 0.000000;
        isotopicDistribution[1][0] = 1.000000;
        isotopicDistribution[1][1] = 0.555776;
        isotopicDistribution[1][2] = 0.178136;
        isotopicDistribution[1][3] = 0.041790;
        isotopicDistribution[1][4] = 0.007894;
        isotopicDistribution[1][5] = 0.001263;
        isotopicDistribution[1][6] = 0.000000;
        isotopicDistribution[1][7] = 0.000000;
        isotopicDistribution[1][8] = 0.000000;
        isotopicDistribution[1][9] = 0.000000;
        isotopicDistribution[1][10] = 0.000000;
        isotopicDistribution[1][11] = 0.000000;
        isotopicDistribution[1][12] = 0.000000;
        isotopicDistribution[1][13] = 0.000000;
        isotopicDistribution[1][14] = 0.000000;
        isotopicDistribution[1][15] = 0.000000;
        isotopicDistribution[1][16] = 0.000000;
        isotopicDistribution[1][17] = 0.000000;
        isotopicDistribution[1][18] = 0.000000;
        isotopicDistribution[1][19] = 0.000000;
        isotopicDistribution[2][0] = 1.000000;
        isotopicDistribution[2][1] = 0.845212;
        isotopicDistribution[2][2] = 0.437964;
        isotopicDistribution[2][3] = 0.168570;
        isotopicDistribution[2][4] = 0.052286;
        isotopicDistribution[2][5] = 0.013663;
        isotopicDistribution[2][6] = 0.003097;
        isotopicDistribution[2][7] = 0.000000;
        isotopicDistribution[2][8] = 0.000000;
        isotopicDistribution[2][9] = 0.000000;
        isotopicDistribution[2][10] = 0.000000;
        isotopicDistribution[2][11] = 0.000000;
        isotopicDistribution[2][12] = 0.000000;
        isotopicDistribution[2][13] = 0.000000;
        isotopicDistribution[2][14] = 0.000000;
        isotopicDistribution[2][15] = 0.000000;
        isotopicDistribution[2][16] = 0.000000;
        isotopicDistribution[2][17] = 0.000000;
        isotopicDistribution[2][18] = 0.000000;
        isotopicDistribution[2][19] = 0.000000;
        isotopicDistribution[3][0] = 0.891137;
        isotopicDistribution[3][1] = 1.000000;
        isotopicDistribution[3][2] = 0.644526;
        isotopicDistribution[3][3] = 0.303221;
        isotopicDistribution[3][4] = 0.114261;
        isotopicDistribution[3][5] = 0.036227;
        isotopicDistribution[3][6] = 0.009967;
        isotopicDistribution[3][7] = 0.002431;
        isotopicDistribution[3][8] = 0.000000;
        isotopicDistribution[3][9] = 0.000000;
        isotopicDistribution[3][10] = 0.000000;
        isotopicDistribution[3][11] = 0.000000;
        isotopicDistribution[3][12] = 0.000000;
        isotopicDistribution[3][13] = 0.000000;
        isotopicDistribution[3][14] = 0.000000;
        isotopicDistribution[3][15] = 0.000000;
        isotopicDistribution[3][16] = 0.000000;
        isotopicDistribution[3][17] = 0.000000;
        isotopicDistribution[3][18] = 0.000000;
        isotopicDistribution[3][19] = 0.000000;
        isotopicDistribution[4][0] = 0.712904;
        isotopicDistribution[4][1] = 1.000000;
        isotopicDistribution[4][2] = 0.775825;
        isotopicDistribution[4][3] = 0.432162;
        isotopicDistribution[4][4] = 0.191232;
        isotopicDistribution[4][5] = 0.070896;
        isotopicDistribution[4][6] = 0.022756;
        isotopicDistribution[4][7] = 0.006467;
        isotopicDistribution[4][8] = 0.001654;
        isotopicDistribution[4][9] = 0.000000;
        isotopicDistribution[4][10] = 0.000000;
        isotopicDistribution[4][11] = 0.000000;
        isotopicDistribution[4][12] = 0.000000;
        isotopicDistribution[4][13] = 0.000000;
        isotopicDistribution[4][14] = 0.000000;
        isotopicDistribution[4][15] = 0.000000;
        isotopicDistribution[4][16] = 0.000000;
        isotopicDistribution[4][17] = 0.000000;
        isotopicDistribution[4][18] = 0.000000;
        isotopicDistribution[4][19] = 0.000000;
        isotopicDistribution[5][0] = 0.595410;
        isotopicDistribution[5][1] = 1.000000;
        isotopicDistribution[5][2] = 0.909613;
        isotopicDistribution[5][3] = 0.587263;
        isotopicDistribution[5][4] = 0.299194;
        isotopicDistribution[5][5] = 0.127213;
        isotopicDistribution[5][6] = 0.046724;
        isotopicDistribution[5][7] = 0.015174;
        isotopicDistribution[5][8] = 0.004431;
        isotopicDistribution[5][9] = 0.001178;
        isotopicDistribution[5][10] = 0.000000;
        isotopicDistribution[5][11] = 0.000000;
        isotopicDistribution[5][12] = 0.000000;
        isotopicDistribution[5][13] = 0.000000;
        isotopicDistribution[5][14] = 0.000000;
        isotopicDistribution[5][15] = 0.000000;
        isotopicDistribution[5][16] = 0.000000;
        isotopicDistribution[5][17] = 0.000000;
        isotopicDistribution[5][18] = 0.000000;
        isotopicDistribution[5][19] = 0.000000;
        isotopicDistribution[6][0] = 0.484866;
        isotopicDistribution[6][1] = 0.953177;
        isotopicDistribution[6][2] = 1.000000;
        isotopicDistribution[6][3] = 0.737825;
        isotopicDistribution[6][4] = 0.427036;
        isotopicDistribution[6][5] = 0.205462;
        isotopicDistribution[6][6] = 0.085169;
        isotopicDistribution[6][7] = 0.031161;
        isotopicDistribution[6][8] = 0.010239;
        isotopicDistribution[6][9] = 0.003062;
        isotopicDistribution[6][10] = 0.000000;
        isotopicDistribution[6][11] = 0.000000;
        isotopicDistribution[6][12] = 0.000000;
        isotopicDistribution[6][13] = 0.000000;
        isotopicDistribution[6][14] = 0.000000;
        isotopicDistribution[6][15] = 0.000000;
        isotopicDistribution[6][16] = 0.000000;
        isotopicDistribution[6][17] = 0.000000;
        isotopicDistribution[6][18] = 0.000000;
        isotopicDistribution[6][19] = 0.000000;
        isotopicDistribution[7][0] = 0.368757;
        isotopicDistribution[7][1] = 0.828938;
        isotopicDistribution[7][2] = 1.000000;
        isotopicDistribution[7][3] = 0.851429;
        isotopicDistribution[7][4] = 0.570087;
        isotopicDistribution[7][5] = 0.317911;
        isotopicDistribution[7][6] = 0.152962;
        isotopicDistribution[7][7] = 0.065031;
        isotopicDistribution[7][8] = 0.024851;
        isotopicDistribution[7][9] = 0.008647;
        isotopicDistribution[7][10] = 0.002767;
        isotopicDistribution[7][11] = 0.000000;
        isotopicDistribution[7][12] = 0.000000;
        isotopicDistribution[7][13] = 0.000000;
        isotopicDistribution[7][14] = 0.000000;
        isotopicDistribution[7][15] = 0.000000;
        isotopicDistribution[7][16] = 0.000000;
        isotopicDistribution[7][17] = 0.000000;
        isotopicDistribution[7][18] = 0.000000;
        isotopicDistribution[7][19] = 0.000000;
        isotopicDistribution[8][0] = 0.295369;
        isotopicDistribution[8][1] = 0.745770;
        isotopicDistribution[8][2] = 1.000000;
        isotopicDistribution[8][3] = 0.939923;
        isotopicDistribution[8][4] = 0.691490;
        isotopicDistribution[8][5] = 0.422289;
        isotopicDistribution[8][6] = 0.221980;
        isotopicDistribution[8][7] = 0.102927;
        isotopicDistribution[8][8] = 0.042845;
        isotopicDistribution[8][9] = 0.016224;
        isotopicDistribution[8][10] = 0.005647;
        isotopicDistribution[8][11] = 0.001821;
        isotopicDistribution[8][12] = 0.000000;
        isotopicDistribution[8][13] = 0.000000;
        isotopicDistribution[8][14] = 0.000000;
        isotopicDistribution[8][15] = 0.000000;
        isotopicDistribution[8][16] = 0.000000;
        isotopicDistribution[8][17] = 0.000000;
        isotopicDistribution[8][18] = 0.000000;
        isotopicDistribution[8][19] = 0.000000;
        isotopicDistribution[9][0] = 0.234948;
        isotopicDistribution[9][1] = 0.658283;
        isotopicDistribution[9][2] = 0.971762;
        isotopicDistribution[9][3] = 1.000000;
        isotopicDistribution[9][4] = 0.802247;
        isotopicDistribution[9][5] = 0.532683;
        isotopicDistribution[9][6] = 0.303778;
        isotopicDistribution[9][7] = 0.152559;
        isotopicDistribution[9][8] = 0.068695;
        isotopicDistribution[9][9] = 0.028112;
        isotopicDistribution[9][10] = 0.010566;
        isotopicDistribution[9][11] = 0.003678;
        isotopicDistribution[9][12] = 0.001194;
        isotopicDistribution[9][13] = 0.000000;
        isotopicDistribution[9][14] = 0.000000;
        isotopicDistribution[9][15] = 0.000000;
        isotopicDistribution[9][16] = 0.000000;
        isotopicDistribution[9][17] = 0.000000;
        isotopicDistribution[9][18] = 0.000000;
        isotopicDistribution[9][19] = 0.000000;
        isotopicDistribution[10][0] = 0.178508;
        isotopicDistribution[10][1] = 0.551598;
        isotopicDistribution[10][2] = 0.891811;
        isotopicDistribution[10][3] = 1.000000;
        isotopicDistribution[10][4] = 0.870814;
        isotopicDistribution[10][5] = 0.625779;
        isotopicDistribution[10][6] = 0.385337;
        isotopicDistribution[10][7] = 0.208576;
        isotopicDistribution[10][8] = 0.101081;
        isotopicDistribution[10][9] = 0.044468;
        isotopicDistribution[10][10] = 0.017951;
        isotopicDistribution[10][11] = 0.006706;
        isotopicDistribution[10][12] = 0.002335;
        isotopicDistribution[10][13] = 0.000000;
        isotopicDistribution[10][14] = 0.000000;
        isotopicDistribution[10][15] = 0.000000;
        isotopicDistribution[10][16] = 0.000000;
        isotopicDistribution[10][17] = 0.000000;
        isotopicDistribution[10][18] = 0.000000;
        isotopicDistribution[10][19] = 0.000000;
        isotopicDistribution[11][0] = 0.139841;
        isotopicDistribution[11][1] = 0.470844;
        isotopicDistribution[11][2] = 0.825470;
        isotopicDistribution[11][3] = 1.000000;
        isotopicDistribution[11][4] = 0.938111;
        isotopicDistribution[11][5] = 0.724608;
        isotopicDistribution[11][6] = 0.478745;
        isotopicDistribution[11][7] = 0.277647;
        isotopicDistribution[11][8] = 0.144001;
        isotopicDistribution[11][9] = 0.067736;
        isotopicDistribution[11][10] = 0.029214;
        isotopicDistribution[11][11] = 0.011655;
        isotopicDistribution[11][12] = 0.004331;
        isotopicDistribution[11][13] = 0.001508;
        isotopicDistribution[11][14] = 0.000000;
        isotopicDistribution[11][15] = 0.000000;
        isotopicDistribution[11][16] = 0.000000;
        isotopicDistribution[11][17] = 0.000000;
        isotopicDistribution[11][18] = 0.000000;
        isotopicDistribution[11][19] = 0.000000;
        isotopicDistribution[12][0] = 0.110880;
        isotopicDistribution[12][1] = 0.404250;
        isotopicDistribution[12][2] = 0.764124;
        isotopicDistribution[12][3] = 0.994691;
        isotopicDistribution[12][4] = 1.000000;
        isotopicDistribution[12][5] = 0.825970;
        isotopicDistribution[12][6] = 0.582522;
        isotopicDistribution[12][7] = 0.360096;
        isotopicDistribution[12][8] = 0.198832;
        isotopicDistribution[12][9] = 0.099472;
        isotopicDistribution[12][10] = 0.045591;
        isotopicDistribution[12][11] = 0.019314;
        isotopicDistribution[12][12] = 0.007618;
        isotopicDistribution[12][13] = 0.002814;
        isotopicDistribution[12][14] = 0.000000;
        isotopicDistribution[12][15] = 0.000000;
        isotopicDistribution[12][16] = 0.000000;
        isotopicDistribution[12][17] = 0.000000;
        isotopicDistribution[12][18] = 0.000000;
        isotopicDistribution[12][19] = 0.000000;
        isotopicDistribution[13][0] = 0.081380;
        isotopicDistribution[13][1] = 0.319627;
        isotopicDistribution[13][2] = 0.652303;
        isotopicDistribution[13][3] = 0.918383;
        isotopicDistribution[13][4] = 1.000000;
        isotopicDistribution[13][5] = 0.895645;
        isotopicDistribution[13][6] = 0.685621;
        isotopicDistribution[13][7] = 0.460421;
        isotopicDistribution[13][8] = 0.276378;
        isotopicDistribution[13][9] = 0.150407;
        isotopicDistribution[13][10] = 0.075030;
        isotopicDistribution[13][11] = 0.034611;
        isotopicDistribution[13][12] = 0.014871;
        isotopicDistribution[13][13] = 0.005987;
        isotopicDistribution[13][14] = 0.002270;
        isotopicDistribution[13][15] = 0.000000;
        isotopicDistribution[13][16] = 0.000000;
        isotopicDistribution[13][17] = 0.000000;
        isotopicDistribution[13][18] = 0.000000;
        isotopicDistribution[13][19] = 0.000000;
        isotopicDistribution[14][0] = 0.062581;
        isotopicDistribution[14][1] = 0.263702;
        isotopicDistribution[14][2] = 0.575328;
        isotopicDistribution[14][3] = 0.863482;
        isotopicDistribution[14][4] = 1.000000;
        isotopicDistribution[14][5] = 0.950817;
        isotopicDistribution[14][6] = 0.771506;
        isotopicDistribution[14][7] = 0.548469;
        isotopicDistribution[14][8] = 0.348161;
        isotopicDistribution[14][9] = 0.200189;
        isotopicDistribution[14][10] = 0.105433;
        isotopicDistribution[14][11] = 0.051316;
        isotopicDistribution[14][12] = 0.023251;
        isotopicDistribution[14][13] = 0.009867;
        isotopicDistribution[14][14] = 0.003941;
        isotopicDistribution[14][15] = 0.001489;
        isotopicDistribution[14][16] = 0.000000;
        isotopicDistribution[14][17] = 0.000000;
        isotopicDistribution[14][18] = 0.000000;
        isotopicDistribution[14][19] = 0.000000;
        isotopicDistribution[15][0] = 0.049015;
        isotopicDistribution[15][1] = 0.220215;
        isotopicDistribution[15][2] = 0.510673;
        isotopicDistribution[15][3] = 0.812617;
        isotopicDistribution[15][4] = 0.995728;
        isotopicDistribution[15][5] = 1.000000;
        isotopicDistribution[15][6] = 0.855816;
        isotopicDistribution[15][7] = 0.640920;
        isotopicDistribution[15][8] = 0.428150;
        isotopicDistribution[15][9] = 0.258844;
        isotopicDistribution[15][10] = 0.143228;
        isotopicDistribution[15][11] = 0.073195;
        isotopicDistribution[15][12] = 0.034802;
        isotopicDistribution[15][13] = 0.015490;
        isotopicDistribution[15][14] = 0.006487;
        isotopicDistribution[15][15] = 0.002568;
        isotopicDistribution[15][16] = 0.000000;
        isotopicDistribution[15][17] = 0.000000;
        isotopicDistribution[15][18] = 0.000000;
        isotopicDistribution[15][19] = 0.000000;
        isotopicDistribution[16][0] = 0.037099;
        isotopicDistribution[16][1] = 0.176952;
        isotopicDistribution[16][2] = 0.434584;
        isotopicDistribution[16][3] = 0.730909;
        isotopicDistribution[16][4] = 0.945007;
        isotopicDistribution[16][5] = 1.000000;
        isotopicDistribution[16][6] = 0.900675;
        isotopicDistribution[16][7] = 0.709153;
        isotopicDistribution[16][8] = 0.497628;
        isotopicDistribution[16][9] = 0.315790;
        isotopicDistribution[16][10] = 0.183301;
        isotopicDistribution[16][11] = 0.098210;
        isotopicDistribution[16][12] = 0.048934;
        isotopicDistribution[16][13] = 0.022815;
        isotopicDistribution[16][14] = 0.010006;
        isotopicDistribution[16][15] = 0.004146;
        isotopicDistribution[16][16] = 0.001630;
        isotopicDistribution[16][17] = 0.000000;
        isotopicDistribution[16][18] = 0.000000;
        isotopicDistribution[16][19] = 0.000000;
        isotopicDistribution[17][0] = 0.028489;
        isotopicDistribution[17][1] = 0.143772;
        isotopicDistribution[17][2] = 0.372798;
        isotopicDistribution[17][3] = 0.660813;
        isotopicDistribution[17][4] = 0.899122;
        isotopicDistribution[17][5] = 1.000000;
        isotopicDistribution[17][6] = 0.945614;
        isotopicDistribution[17][7] = 0.780955;
        isotopicDistribution[17][8] = 0.574359;
        isotopicDistribution[17][9] = 0.381740;
        isotopicDistribution[17][10] = 0.231932;
        isotopicDistribution[17][11] = 0.130003;
        isotopicDistribution[17][12] = 0.067735;
        isotopicDistribution[17][13] = 0.033010;
        isotopicDistribution[17][14] = 0.015127;
        isotopicDistribution[17][15] = 0.006548;
        isotopicDistribution[17][16] = 0.002688;
        isotopicDistribution[17][17] = 0.001050;
        isotopicDistribution[17][18] = 0.021473;
        isotopicDistribution[17][19] = 0.000000;
        isotopicDistribution[18][0] = 0.114423;
        isotopicDistribution[18][1] = 0.313596;
        isotopicDistribution[18][2] = 0.588027;
        isotopicDistribution[18][3] = 0.846974;
        isotopicDistribution[18][4] = 0.997820;
        isotopicDistribution[18][5] = 1.000000;
        isotopicDistribution[18][6] = 0.875691;
        isotopicDistribution[18][7] = 0.683169;
        isotopicDistribution[18][8] = 0.481829;
        isotopicDistribution[18][9] = 0.310749;
        isotopicDistribution[18][10] = 0.184951;
        isotopicDistribution[18][11] = 0.102348;
        isotopicDistribution[18][12] = 0.052989;
        isotopicDistribution[18][13] = 0.025802;
        isotopicDistribution[18][14] = 0.011870;
        isotopicDistribution[18][15] = 0.005179;
        isotopicDistribution[18][16] = 0.002150;
        isotopicDistribution[18][17] = 0.000000;
        isotopicDistribution[18][18] = 0.000000;
        isotopicDistribution[18][19] = 0.000000;
        isotopicDistribution[19][0] = 0.016073;
        isotopicDistribution[19][1] = 0.090246;
        isotopicDistribution[19][2] = 0.260110;
        isotopicDistribution[19][3] = 0.512071;
        isotopicDistribution[19][4] = 0.773268;
        isotopicDistribution[19][5] = 0.953917;
        isotopicDistribution[19][6] = 1.000000;
        isotopicDistribution[19][7] = 0.915161;
        isotopicDistribution[19][8] = 0.745551;
        isotopicDistribution[19][9] = 0.548712;
        isotopicDistribution[19][10] = 0.369064;
        isotopicDistribution[19][11] = 0.228958;
        isotopicDistribution[19][12] = 0.132003;
        isotopicDistribution[19][13] = 0.071173;
        isotopicDistribution[19][14] = 0.036079;
        isotopicDistribution[19][15] = 0.017273;
        isotopicDistribution[19][16] = 0.007841;
        isotopicDistribution[19][17] = 0.003386;
        isotopicDistribution[19][18] = 0.001395;
        isotopicDistribution[19][19] = 0.000000;


        loadIntensePeaks();
        loadMostIntensePeaks();
    }

    private static float hplus;
    private static float h;
    private static float oh;
    private static float binWidth;
    private static boolean useMono=true;

    public static void main(String[] args) {
        AssignMass am = new AssignMass(true);
    }

    public static AssignMass getInstance(boolean useMono) {
        if (assignMass == null)
            assignMass = new AssignMass(useMono);

        return assignMass;
    }

    public static float getMass(int i) {
        return aaMasses[i];
    }

    public static List<Double> getFragIonList(String seq, int ion) {

        float[] arr = getFragIonArr(seq, ion);

        List<Double> l = new ArrayList<>();
        for(float f:arr) {
            //   fragList.add((double) f);
            l.add((double) f);
        }

        return l;
    }

    public static float[] getFragIonArr(String seq, int ion) {

        final int size = seq.length();
        float[] arr = new float[size];
        //System.out.println(size + " " + seq.length()+ "");

        int count=0;
        float addMass = 0.0f;

        for(int i=0;i<size;i++) {
            float mass = getMass(seq.charAt(i));

            if(mass<=FLOAT_ZERO) continue;

            //System.out.println(seq.charAt(i) + "\t" + mass);
            addMass += mass;

            switch (ion) {

                /*a*/ case 0: arr[count] = addMass + nTerm +  AssignMass.getBionfragment() - 27.99492f;
                    break;
                /*b*/ case 1: arr[count] = addMass + nTerm + AssignMass.getBionfragment();
//                    System.out.println("---" +  arr[count] + " " + addMass + " " + AssignMass.getBionfragment());
                    break;
                /*c*/ case 2: arr[count] = addMass + nTerm + AssignMass.getBionfragment() + 17.02654f; break;
                ///*x*/ case 6: rarr[rcount] = pepMass - addMass + AssignMass.getcTerm() + 45.0f; break;
                ///*y*/ case 7: rarr[rcount] = pepMass - addMass + AssignMass.getYionfragment(); break;
                ///*z*/ case 8: rarr[rcount] = pepMass - addMass + AssignMass.getcTerm() + 3.0f; break;
            }

            count++;
        }

        return arr;
    }

    public static List<Double> getFragIonListRev(String seq, int ion) {

        float[] arr = getFragIonArrRev(seq, ion);

        List<Double> l = new ArrayList<>();
        for(float f:arr) {
            //   fragList.add((double) f);
            l.add((double) f);
        }

        return l;
    }

    public static float[] getFragIonArrRev(String seq, int ion) {

        //seq="YLK";

        final int size = seq.length();
        float[] rarr = new float[size];

        //System.out.println(size + " " + seq + "\t" + seq.length());

        int rcount=size-1;
        float raddMass = 0.0f;

        for(int i=0;i<size;i++) {
            float rmass = getMass(seq.charAt(rcount));
            if(rmass<=FLOAT_ZERO) continue;
            raddMass += rmass;
            //arr[count] = mass
            //      dAddNterminus

            //System.out.println("iiiiiiii\t" + rcount + " " + seq.charAt(rcount+1) + "\t" + raddMass +"\t" +  AssignMass.getYionfragment() + "\t" + (raddMass + AssignMass.getYionfragment()));
            switch (ion) {
                /*x*/ case 6: rarr[rcount] = raddMass + cTerm + AssignMass.getYionfragment() + 25.97926f; break;
                /*y*/ case 7: rarr[rcount] = raddMass + cTerm + AssignMass.getYionfragment(); break;
                /*z*/ case 8: rarr[rcount] = raddMass + cTerm+ AssignMass.getYionfragment() - 17.02545f; break;
            }


            rcount--;
            // System.out.println(seq.charAt(i) + "\t" + ionToUse + "\t" +  addMass + "\t" + arr.length);
        }

        //for(int i=0;i<arr.length;i++) {
        //    System.out.println(arr[i] + "\t" + rarr[i] + "\t" + AssignMass.getBionfragment());
        //}

        return rarr;
    }


    public static float[] getFragIonArrMod(String seq, int ion, int[] modIndexHash) {

        final int size = seq.length()-1;
        float[] arr = new float[size];
        //System.out.println(size + " " + seq.length()+ "");

        int count=0;
        float addMass = 0.0f;

        for(int i=0;i<size;i++) {
            char ch = seq.charAt(i);
            float mass = getMass(ch);

            if(modIndexHash[i]>0) {

                //System.out.println(modIndexHash + " " + "aaa" + " " + ch + " " + mass + " " + DiffModification.getDiffModMass(ch));
                //  mass += DiffModification.getDiffModMass(ch);
            }


            addMass += mass;

            switch (ion) {
                /*a*/ case 0: arr[count] = addMass + nTerm + AssignMass.getBionfragment() + 27.99492f; break;
                /*b*/ case 1: arr[count] = addMass + nTerm + AssignMass.getBionfragment(); break;
                /*c*/ case 2: arr[count] = addMass + nTerm + AssignMass.getBionfragment()+ 17.02654f; break;
            }

            count++;
        }

        return arr;
    }

    public static float[] getFragIonArrRevMod(String seq, int ion, int[] modIndexHash) {


        final int size = seq.length();
        float[] rarr = new float[size];

        //System.out.println(size + " " + seq + "\t" + seq.length());
        int rcount=size-1;
        float raddMass = 0.0f;

        for(int i=0;i<size;i++) {
            char ch=seq.charAt(rcount);

            float rmass = getMass(ch);

            if(modIndexHash[size-i]>0) {
                //   System.out.println(ch + "\t1");
                //  rmass += DiffModification.getDiffModMass(ch);
            }

            raddMass += rmass;
            //arr[count] = mass
            //      dAddNterminus

            //System.out.println("iiiiiiii\t" + rcount + " " + seq.charAt(rcount+1) + "\t" + raddMass +"\t" +  AssignMass.getYionfragment() + "\t" + (raddMass + AssignMass.getYionfragment()));
            switch (ion) {
                /*x*/ case 6: rarr[rcount] = raddMass + cTerm + AssignMass.getYionfragment() + 25.97926f; break;
                /*y*/ case 7: rarr[rcount] = raddMass + cTerm + AssignMass.getYionfragment(); break;
                /*z*/ case 8: rarr[rcount] = raddMass + cTerm + AssignMass.getYionfragment() - 17.02545f; break;
            }


            rcount--;
            // System.out.println(seq.charAt(i) + "\t" + ionToUse + "\t" +  addMass + "\t" + arr.length);
        }

        //for(int i=0;i<arr.length;i++) {
        //    System.out.println(arr[i] + "\t" + rarr[i] + "\t" + AssignMass.getBionfragment());
        //}

        return rarr;
    }

    public static void addMass(int i, float mass) {

        if(mass<=0) return;

        //SearchParams.addStaticParam((char)i, mass);
        aaMasses[i] += mass;
    }

    public AssignMass(boolean useMono) {
        //void ASSIGN_MASS(float *pdAAMass,int bUseMonoMasses,float *dHplus, float *dH, float *dOH)

        this.useMono = useMono;

//                System.out.println("=======" + useMono);

        //pdAAMass = new float[SIZE];
        if (useMono) {
            aaMasses = aaMassMono;

            this.binWidth = 1.0005079f;
            this.hplus = 1.0072765f;
            this.h = 1.0078250f;
            this.oh = 15.9949146f + this.h;
        } else /* average masses */
        {
            aaMasses = aaMassAvg;
            this.binWidth = 1.0011413f;
            this.hplus = 1.00739f;
            this.h = 1.00794f;
            this.oh = 15.9994f + this.h;
        }

    }

    public static float getHplus() {
        return hplus;
    }

    public static void setHplus(float hplus) {
        AssignMass.hplus = hplus;
    }

    public static float getH() {
        return h;
    }

    public static void setH(float h) {
        AssignMass.h = h;
    }

    public static float getOh() {
        return oh;
    }

    public static void setOh(float oh) {
        AssignMass.oh = oh;
    }

    public static float getBinWidth() {
        return binWidth;
    }

    public static void setBinWidth(float binWidth) {
        AssignMass.binWidth = binWidth;
    }



    public float[] getPdAAMass() {
        if(useMono)
            return aaMassMono;
        else
            return aaMassAvg;
    }


    public static float[] getAaMasses() {
        return aaMasses;
    }

    public static void setAaMasses(float[] aaMasses) {
        AssignMass.aaMasses = aaMasses;
    }

    public boolean isUseMono() {
        return useMono;
    }

    public void setUseMono(boolean useMono) {
        this.useMono = useMono;
    }

    public static AssignMass getAssignMass() {
        return assignMass;
    }

    public static void setAssignMass(AssignMass assignMass) {
        AssignMass.assignMass = assignMass;
    }

    public static float getcTerm() {
        return cTerm;
    }

    public static void setcTerm(float cTerm) {
        AssignMass.cTerm = cTerm;
    }

    public static float getnTerm() {
        return nTerm;
    }

    public static void setnTerm(float nTerm) {
        AssignMass.nTerm = nTerm;
    }

    public static float getBionfragment() {
        return bionfragment;
    }

    public static float getAionfragment() {
        return aionfragment;
    }

    public static void setAionfragment(float aionfragment) {
        AssignMass.aionfragment = aionfragment;
    }

    public static float getCionfragment() {
        return cionfragment;
    }

    public static void setCionfragment(float cionfragment) {
        AssignMass.cionfragment = cionfragment;
    }

    public static void setBionfragment(float bionfragment) {
        AssignMass.bionfragment = bionfragment;
    }

    public static float getXionfragment() {
        return xionfragment;
    }

    public static void setXionfragment(float xionfragment) {
        AssignMass.xionfragment = xionfragment;
    }

    public static float getYionfragment() {
        return yionfragment;
    }

    public static void setYionfragment(float yionfragment) {
        AssignMass.yionfragment = yionfragment;
    }

    public static float getZionfragment() {
        return zionfragment;
    }
    public static void setZionfragment(float zionfragment) {
        AssignMass.zionfragment = zionfragment;
    }

    public static int getBinnedValue(float mass, float shift) {

        //System.out.println("===" + mass + " " + shift+ " " + AssignMass.getBinWidth() + " " + (int)((mass+shift)/AssignMass.getBinWidth() + 0.5) + " " + ((mass+shift)/AssignMass.getBinWidth() + 0.5) + " " + (int)(mass+shift+ 0.5));
        //System.out.println( (int)((mass+shift)/AssignMass.getBinWidth() + 0.5) + " " + (int)(mass+shift+ 0.5) + " " +  ((int)((mass+shift)/AssignMass.getBinWidth() + 0.5) == (int)(mass+shift+ 0.5)) );
        //System.out.println("===" + mass + " " + shift+ " " + AssignMass.getBinWidth() + " " + (int)((mass+shift)/AssignMass.getBinWidth() + 0.5) + " " + (int)(mass+shift+ 0.5));
        // return (int)( (mass+shift)/AssignMass.getBinWidth() + 0.5);

//System.out.println("frag ion ===\t"  + mass + "\t" + (int)( (mass+shift)/AssignMass.getBinWidth() + 1.5));
        return (int)( (mass+shift)/AssignMass.getBinWidth() + 0.5);
        //return (int)( (mass+shift)+ 0.5);
    }

    public static void loadIntensePeaks() {
        for(int i = 0; i < 20; i++) {
            int numPeaks = 0;
            for(int j = 0; j < 20; j++) {

                if(isotopicDistribution[i][j] >= .25) {
                    numPeaks++;
                } else {
                }
            }
            intensePeaks[i]  = new int[numPeaks];
            for(int j = 0; j < 20; j++) {
                if(isotopicDistribution[i][j] >= .25) {
                    intensePeaks[i][--numPeaks] = j;
                }
            }

        }
    }
    public static void loadMostIntensePeaks() {
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                if(isotopicDistribution[i][j] == 1.0f) {
                    mostIntensePeaks[i] = j;
                    break;
                }
            }

        }
    }
}
