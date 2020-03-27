package com.zh.sparse;

/**
 * 模拟五子棋盘 11*11大小
 * 二维数组实现 - 稀疏数组加强
 */
public class SparseArraysDemo {

    public static void main(String[] args) {
        //模拟11*11大小的五子棋盘
        int[][] chessArray = new int[11][11];
        //假设在棋盘有一颗白棋(2row、3col)和黑棋(3row、4col)
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        //循环看看是否正确
        for (int[] cols : chessArray) {
            for (int col : cols) {
                System.out.printf("%d\t", col);
            }
            System.out.println();
        }

        //原二维数组存储的棋盘数据存在很多相同且无效的值, 非常浪费空间; -> 优化存储: 原二维数组存储的棋盘 - 存储为稀松数组
        //循环原二维数组, 记录棋盘行数&列数 以及 其中的有效数据个数
        int rowCount = chessArray.length;
        int colCount = chessArray[0].length;
        int count = 0;
        for (int[] cols : chessArray) {
            for (int col : cols) {
                if (col > 0) {
                    count++;
                }
            }
        }
        //创建稀松数组
        int[][] sparseArray = new int[count + 1][3];
        //第一行存储为棋盘行列数 以及 有效数据的个数
        sparseArray[0][0] = rowCount;
        sparseArray[0][1] = colCount;
        sparseArray[0][2] = count;
        //再次遍历原二维数组, 为稀松数组赋值
        int number = 0;//记录是第几个非0数据
        for (int i = 0; i< chessArray.length; i++) {
            for (int j = 0; j < chessArray[0].length; j++) {
                if (chessArray[i][j] != 0) {
                    number++;
                    sparseArray[number][0] = i;
                    sparseArray[number][1] = j;
                    sparseArray[number][2] = chessArray[i][j];
                }
            }
        }
        //分割线
        System.out.println("-----------------------------------------------------");
        //循环稀疏数组 看看是否正确
        for (int[] cols : sparseArray) {
            for (int col : cols) {
                System.out.printf("%d\t", col);
            }
            System.out.println();
        }

        //以上模拟数据压缩完毕, 以下模拟压缩读取回复

        //恢复稀松数据为原二维数组
        int[][] chessArray2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        //取出稀松数组中的有效数据, 填充到对应位置
        for (int i = 1; i < sparseArray.length; i++) {
            chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        //分割线
        System.out.println("-----------------------------------------------------");
        //循环看看恢复是否成功
        for (int[] cols : chessArray2) {
            for (int col : cols) {
                System.out.printf("%d\t", col);
            }
            System.out.println();
        }
    }
}
