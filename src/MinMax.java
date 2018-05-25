
import java.util.ArrayList;


import javax.swing.JButton;


public class MinMax {
    private static int dimSize = GameField.dimSize;
    public static int depth=1;
    public static int test=0;
    public static int getMoveId(ArrayList<JButton> listOfButtons)
    {

        ArrayList<ArrayList<JButton>> childs = getChildBoards(listOfButtons);

        int bestValue = -100000;
        int bestIndex =-500;

        for(ArrayList<JButton> element: childs)
        {
            int index = getClickedButtonIndex(listOfButtons, element);
            int point = calculatePoints(index, listOfButtons);

            int minMaxValue = minMax(element, depth,true , point);
            if(minMaxValue > bestValue)
            {
                bestValue = minMaxValue;
                bestIndex = index;
            }

        }

        return bestIndex;
    }
    public static  int checkVerical(int buttonId, ArrayList<JButton> listOfButtons)
    {

        for(int i = buttonId; i>-1;i=i-dimSize)
        {
            if(listOfButtons.get(i).isEnabled())
                return 0;

        }
        for(int i = buttonId; i<listOfButtons.size();i=i+dimSize)
        {
            if(listOfButtons.get(i).isEnabled())
                return 0;

        }

        return dimSize;
    }
    public static int checkHorizontal(int buttonId, ArrayList<JButton> listOfButtons)
    {
        for (int i =1; i<=buttonId % dimSize;i++)
        {
            if(listOfButtons.get(buttonId-i).isEnabled()) return 0;
        }
        for (int i =1; i<=dimSize-1-(buttonId % dimSize);i++)
        {
            if(listOfButtons.get(buttonId+i).isEnabled()) return 0;
        }
        return dimSize;
    }
    public static int checkCrossWiseLeft(int buttonId, ArrayList<JButton> listOfButtons)
    {
        int returnvalue = 0;
        boolean flag = true;
        for(int i =buttonId-dimSize-1; (i>-1) && (dimSize-1 != i % dimSize);i = i-dimSize-1)
        {
            if(listOfButtons.get(i).isEnabled()) flag = false;
            returnvalue++;
        }

        for(int i =buttonId+1+dimSize; i < (listOfButtons.size()) && (0 != i % dimSize);i = i+dimSize+1)
        {
            if(listOfButtons.get(i).isEnabled()) flag = false;
            returnvalue++;
        }

        returnvalue++;
        if(flag)
        {
            return returnvalue >1 ? returnvalue:0;
        }
        return 0;

    }
    public static int checkCrossWiseRight(int buttonId, ArrayList<JButton> listOfButtons)
    {
        int returnvalue = 0;
        boolean flag = true;
        for(int i =buttonId+dimSize-1; i < (listOfButtons.size()) && (dimSize-1 != i % dimSize);i = i+dimSize-1)
        {
            if(listOfButtons.get(i).isEnabled()) flag = false;
            returnvalue++;
        }

        for(int i =buttonId+1-dimSize; i >-1 && (0 != i % dimSize);i = i-dimSize+1)
        {
            if(listOfButtons.get(i).isEnabled()) flag = false;
            returnvalue++;
        }

        returnvalue++;
        if(flag)
        {
            return returnvalue >1 ? returnvalue:0;
        }
        return 0;

    }
    public static int calculatePoints(int position, ArrayList<JButton> listOfButtons)
    {
        return checkVerical(position,listOfButtons) + checkHorizontal(position,listOfButtons) + checkCrossWiseLeft(position,listOfButtons) + checkCrossWiseRight(position,listOfButtons);
    }
    private static int minMax(ArrayList<JButton> listOfButtons, int searchDepth,boolean isRedPlayerTurn, int points)
    {
        if (searchDepth ==0 || isBoardFull(listOfButtons))
        {
            test++;
            System.out.println(test);
            return points;
        }

        ArrayList<ArrayList<JButton>> childs = getChildBoards(listOfButtons);

        if(isRedPlayerTurn)
        {
            int bestValue = 10000;
            for(ArrayList<JButton> element: childs)
            {
                int currentIndex = getClickedButtonIndex(listOfButtons, element);
                int point = calculatePoints(currentIndex, listOfButtons);

                int value = minMax(element, searchDepth-1, false, points-point);
                bestValue = Math.min(bestValue, value);
            }
            return bestValue;
        }
        else
        {
            int bestValue = -10000;
            for(ArrayList<JButton> element: childs)
            {
                int currentIndex = getClickedButtonIndex(listOfButtons, element);
                int point = calculatePoints(currentIndex, listOfButtons);

                int value = minMax(element, searchDepth-1, true, points+point);
                bestValue = Math.max(bestValue, value);
            }
            return bestValue;
        }

    }
    private static ArrayList<JButton> copyBoard(ArrayList<JButton> source)
    {
        ArrayList<JButton> returnList = new ArrayList<>();
        for(JButton button : source)
        {
            JButton newButton = new JButton();
            newButton.setActionCommand(button.getActionCommand());
            newButton.setEnabled(button.isEnabled());
            returnList.add(newButton);
        }
        return returnList;
    }
    private static ArrayList<ArrayList<JButton>> getChildBoards(ArrayList<JButton> source)
    {

        ArrayList<ArrayList<JButton>> returnList = new ArrayList<>();
        for(JButton button:source)
        {
            if(button.isEnabled())
            {
                ArrayList<JButton> newChildBoard= copyBoard(source);
                newChildBoard.get(source.indexOf(button)).setEnabled(false);
                returnList.add(newChildBoard);
            }
        }
        return returnList;

    }
    private static int getClickedButtonIndex(ArrayList<JButton> original, ArrayList<JButton> modified)
    {
        for(int i = 0;i<original.size();i++)
        {
            if(original.get(i).isEnabled() && !modified.get(i).isEnabled()) return i;
        }
        return -9999;
    }
    private static boolean isBoardFull(ArrayList<JButton > source)
    {
        int value = 0;
        for (JButton jButton : source) {
            if(!jButton.isEnabled()) value++;
        }
        return value == source.size() ? true : false;
    }

}
