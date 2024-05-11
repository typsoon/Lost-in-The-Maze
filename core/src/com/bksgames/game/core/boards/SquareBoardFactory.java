package com.bksgames.game.core.boards;

import com.bksgames.game.core.Nexus;
import com.bksgames.game.core.Parameters;
import com.bksgames.game.core.tiles.Tunnel;
import com.bksgames.game.core.tiles.Wall;
import com.bksgames.game.enums.PlayerColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class SquareBoardFactory
{
    private static final int baseSize = 7;

    static public SquareBoard CreateSBFor2Players(Parameters parameters)    {
        SquareBoard board = new SquareBoard(Math.max(parameters.mapSize, 27)); // do przemyslenia
        board.playerNexuses.put(PlayerColor.RED,new HashSet<>());
        board.playerNexuses.put(PlayerColor.BLUE,new HashSet<>());

        Random rng = new Random();
        int[][] genGrid = new int[board.size][board.size];
        int realSize = board.size - (board.size%3);
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
        BoardGenerationUtils.randomPath(fpSplit,spSplit,3,genGrid,board.size,board.size);
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

        BoardGenerationUtils.randomPath(sdConSP,sdConFP,3,genGrid,board.size,board.size);
        BoardGenerationUtils.randomPath(tdConSP,tdConFP,3,genGrid,board.size,board.size);


        BoardGenerationUtils.generateRest(genGrid,board.size,board.size,3);




        for(int y=0;y<board.size;y++)
        {
            for(int x=0;x<board.size;x++)
            {
                if(genGrid[x][y] == 0)
                    board.grid[x][y] = new Wall();
                else if(genGrid[x][y] == 3)
                    board.grid[x][y] = new Tunnel();
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
                    board.grid[actFP.x][actFP.y] = new Nexus(PlayerColor.RED,actFP.x, actFP.y,parameters.nexusHitPoints);
                    board.playerNexuses.get(PlayerColor.RED).add((Nexus) board.grid[actFP.x][actFP.y]);
                    board.grid[actSP.x][actSP.y] = new Nexus(PlayerColor.BLUE,actSP.x, actSP.y,parameters.nexusHitPoints);
                    board.playerNexuses.get(PlayerColor.BLUE).add((Nexus) board.grid[actSP.x][actSP.y]);
                    continue;
                }
                if(!(x==1 || x==baseSize || y==1 || y==baseSize))
                {
                    board.grid[actFP.x][actFP.y] = new Tunnel();
                    board.grid[actSP.x][actSP.y] = new Tunnel();
                    continue;
                }
                if(BoardGenerationUtils.adjacent(actFP,fpSplit) || BoardGenerationUtils.adjacent(actFP,sdConFP) ||
                        BoardGenerationUtils.adjacent(actFP,tdConFP))
                    board.grid[actFP.x][actFP.y] = new Tunnel();
                else
                    board.grid[actFP.x][actFP.y] = new Wall();

                if(BoardGenerationUtils.adjacent(actSP,spSplit) || BoardGenerationUtils.adjacent(actSP,sdConSP) ||
                        BoardGenerationUtils.adjacent(actSP,tdConSP))
                    board.grid[actSP.x][actSP.y] = new Tunnel();
                else
                    board.grid[actSP.x][actSP.y] = new Wall();
            }
        }
        return board;
    }
}
