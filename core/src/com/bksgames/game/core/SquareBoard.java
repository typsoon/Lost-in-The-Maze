package com.bksgames.game.core;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SquareBoard implements Board {

    private final Tile[][] grid;
    private final int size;

    private static final int baseSize = 7;



    @Override
    public Tile getTile(int x, int y) {
        if(x>=size || y>=size || x<0 || y<0)      //exception do dodania
            return null;
        return grid[x][y];
    }

    @Override
    public int getWidth() {
        return size;
    }

    @Override
    public int getHeight() {
        return size;
    }

    public SquareBoard(int s, Parameters parameters)    {

        size = Math.max(s, 27);

        grid = new Tile[size][size];


        Random rng = new Random();
        int[][] genGrid = new int[size][size];
        int realSize = size - (size%3);
        int sectionSize = realSize/3;

        //============================================NEXUS SECTION
        int fpNexusSec = rng.nextInt(8);
        if(fpNexusSec==4)
            fpNexusSec=8;
        ArrayList<Integer> choose = new ArrayList<>();
        for(int i=0;i<9;i++)
        {
            if(i==4||i==fpNexusSec || i==fpNexusSec+3 || i==fpNexusSec-3)
                continue;
            if(fpNexusSec%3 != 0 && i == fpNexusSec-1)
                continue;
            if(fpNexusSec%3 != 2 && i == fpNexusSec+1)
                continue;
            choose.add(i);
        }
        int spNexusSec = choose.get(rng.nextInt(choose.size()));
        if(fpNexusSec > spNexusSec)
        {
            int pom = spNexusSec;
            spNexusSec=fpNexusSec;
            fpNexusSec=pom;
        }
        //==============================================NEXUS OFFSET
        Point fpNexusOffset = new Point(
                rng.nextInt(sectionSize-baseSize-1) + (fpNexusSec%3) * sectionSize,
                rng.nextInt(sectionSize-baseSize-1) + (fpNexusSec/3) * sectionSize
        );
        Point spNexusOffset = new Point(
                rng.nextInt(sectionSize-baseSize-1) + (spNexusSec%3) * sectionSize,
                rng.nextInt(sectionSize-baseSize-1) + (spNexusSec/3) * sectionSize
        );

        for(int x=1;x<=baseSize;x++)
        {
            for(int y=1;y<=baseSize;y++)
            {
                genGrid[x+fpNexusOffset.x][y+fpNexusOffset.y] = 1;
                genGrid[x+spNexusOffset.x][y+spNexusOffset.y] = 2;
            }
        }

        //==========================================FIRST PATH
        Point fpSplit,spSplit;
        if(fpNexusSec/3 == spNexusSec/3) // w jednym wierszu
        {
            fpSplit = new Point(fpNexusOffset.x+baseSize+1,fpNexusOffset.y+2+rng.nextInt(baseSize-2));
            spSplit = new Point(spNexusOffset.x,spNexusOffset.y+2+rng.nextInt(baseSize-2));
        }
        else if(fpNexusSec%3 == spNexusSec%3) // w jednej kolumnie
        {
            fpSplit = new Point(fpNexusOffset.x+2+rng.nextInt(baseSize-2),fpNexusOffset.y+baseSize+1);
            spSplit = new Point(spNexusOffset.x+2+rng.nextInt(baseSize-2),spNexusOffset.y);
        }
        else //reszta
        {
            ArrayList<Point> conFP = new ArrayList<>();
            ArrayList<Point> conSP = new ArrayList<>();
            for(int i=1;i<baseSize-1;i++){
                conFP.add(new Point(fpNexusOffset.x+1+i,fpNexusOffset.y+baseSize+1));
                conSP.add(new Point(spNexusOffset.x+1+i,spNexusOffset.y));
            }
            if(fpNexusSec%3<spNexusSec%3)
            {
                for(int i=1;i<baseSize-1;i++){
                    conFP.add(new Point(fpNexusOffset.x+baseSize+1,fpNexusOffset.y+1+i));
                    conSP.add(new Point(spNexusOffset.x,spNexusOffset.y+1+i));
                }
            }
            else{
                for(int i=1;i<baseSize-1;i++){
                    conFP.add(new Point(fpNexusOffset.x,fpNexusOffset.y+1+i));
                    conSP.add(new Point(spNexusOffset.x+baseSize+1,spNexusOffset.y+1+i));
                }

            }
            fpSplit = conFP.get(rng.nextInt(conFP.size()));
            spSplit = conSP.get(rng.nextInt(conSP.size()));
        }
        BoardGenerationUtils.randomPath(fpSplit,spSplit,3,genGrid,size,size);
        //====================================SECOND & THIRD PATH
        ArrayList<Point> conFP = new ArrayList<>();
        for(int i=2;i<baseSize;i++)
            conFP.add(new Point(fpNexusOffset.x+i,fpNexusOffset.y));
        for(int i=2;i<baseSize;i++)
            conFP.add(new Point(fpNexusOffset.x+baseSize+1,fpNexusOffset.y+i));
        for(int i=baseSize-1;i>=2;i--)
            conFP.add(new Point(fpNexusOffset.x+i,fpNexusOffset.y+baseSize+1));
        for(int i=baseSize-1;i>=2;i--)
            conFP.add(new Point(fpNexusOffset.x,fpNexusOffset.y+i));
        while(genGrid[conFP.get(0).x][conFP.get(0).y]==0)
            conFP.add(conFP.remove(0));
        while(genGrid[conFP.get(0).x][conFP.get(0).y]!=0)
            conFP.remove(0);
        Point sdConFP = conFP.get(rng.nextInt(conFP.size()/2));
        Point tdConFP = conFP.get(conFP.size() - 1 - rng.nextInt(conFP.size()/2));



        ArrayList<Point> conSP = new ArrayList<>();
        for(int i=2;i<baseSize;i++)
            conSP.add(new Point(spNexusOffset.x+i,spNexusOffset.y));
        for(int i=2;i<baseSize;i++)
            conSP.add(new Point(spNexusOffset.x+baseSize+1,spNexusOffset.y+i));
        for(int i=baseSize-1;i>=2;i--)
            conSP.add(new Point(spNexusOffset.x+i,spNexusOffset.y+baseSize+1));
        for(int i=baseSize-1;i>=2;i--)
            conSP.add(new Point(spNexusOffset.x,spNexusOffset.y+i));
        while(genGrid[conSP.get(0).x][conSP.get(0).y]==0)
            conSP.add(conSP.remove(0));
        while(genGrid[conSP.get(0).x][conSP.get(0).y]!=0)
            conSP.remove(0);
        Point tdConSP = conSP.get(rng.nextInt(conSP.size()/2));
        Point sdConSP = conSP.get(conSP.size() - 1 - rng.nextInt(conSP.size()/2));

        BoardGenerationUtils.randomPath(sdConSP,sdConFP,3,genGrid,size,size);
        BoardGenerationUtils.randomPath(tdConSP,tdConFP,3,genGrid,size,size);


        BoardGenerationUtils.generateRest(genGrid,size,size,3);




        for(int y=0;y<size;y++)
        {
            for(int x=0;x<size;x++)
            {
                if(genGrid[x][y] == 0)
                    grid[x][y] = new Wall();
                else if(genGrid[x][y] == 3)
                    grid[x][y] = new Tunnel();
            }
        }
        Point actFP = new Point(fpNexusOffset.x,fpNexusOffset.y),
                actSP = new Point(spNexusOffset.x,spNexusOffset.y);
        for(int x=1;x<=baseSize;x++)
        {
            actFP.x++;
            actSP.x++;

            actFP.y = fpNexusOffset.y;
            actSP.y = spNexusOffset.y;
            for(int y=1;y<=baseSize;y++)
            {

                actFP.y++;
                actSP.y++;
                if(x==4 && y==4)
                {
                    grid[actFP.x][actFP.y] = new Nexus(null,actFP.x, actFP.y,parameters.nexusHitPoints);
                    grid[actSP.x][actSP.y] = new Nexus(null,actSP.x, actSP.y,parameters.nexusHitPoints);
                    continue;
                }
                if(!(x==1 || x==baseSize || y==1 || y==baseSize))
                {
                    grid[actFP.x][actFP.y] = new Tunnel();
                    grid[actSP.x][actSP.y] = new Tunnel();
                    continue;
                }
                if(BoardGenerationUtils.adjacent(actFP,fpSplit) || BoardGenerationUtils.adjacent(actFP,sdConFP) ||
                        BoardGenerationUtils.adjacent(actFP,tdConFP))
                    grid[actFP.x][actFP.y] = new Tunnel();
                else
                    grid[actFP.x][actFP.y] = new Wall();

                if(BoardGenerationUtils.adjacent(actSP,spSplit) || BoardGenerationUtils.adjacent(actSP,sdConSP) ||
                        BoardGenerationUtils.adjacent(actSP,tdConSP))
                    grid[actSP.x][actSP.y] = new Tunnel();
                else
                    grid[actSP.x][actSP.y] = new Wall();
            }
        }


    }
}
