import java.util.*;

public class ParseCell {

    private static Scanner sc;

    private static boolean isValid_notMissing(String val)
    {
        return !val.isEmpty() && !val.equals("-");
    }

    private static int indexOf(String str, String look)
    {
        int val = str.indexOf(look);
        return (val == -1) ? str.length() : val;
    }

    private static int indexOf(String str, char look)
    {
        int val = str.indexOf(look);
        return (val == -1) ? str.length() : val;
    }

    public static Cell parseCell(String line)
    {
        String[] arr = new String[12];
        int next = 0;

        for(int i = 0; i < arr.length; i++)
        {
            if(!line.isEmpty() && line.charAt(0) == '"')
            {
                line = line.substring(1);
                next = indexOf(line, '"');
                arr[i] = line.substring(0, next);
                line = line.substring(next+1);
                next = indexOf(line, ',');
            }else
            {
                next = indexOf(line, ',');
                arr[i] = line.substring(0, next);
            }
            line = line.substring(Math.min(next+1, line.length()));
            arr[i] = arr[i].trim();
        }

        return generateCell(arr);
    }

    private static Cell generateCell(String[] data)
    {
        Cell cell = new Cell();
        int iter = 0;

        switch (iter) {
            case 0: cell.setOem((!isValid_notMissing(data[iter++])) ? "NULL" : data[iter-1]);
            case 1: cell.setModel((!isValid_notMissing(data[iter++])) ? "NULL" : data[iter-1]);
            case 2:
            {
                try
                {
                    sc = new Scanner(data[iter++].substring(0, indexOf(data[iter-1], ',')));
                    cell.setLaunch_announced(Integer.parseInt(sc.next()));
                } catch (NumberFormatException e)
                {
                    cell.setLaunch_announced(0);
                }
            }
            case 3:
            {
                cell.setLaunch_status((!isValid_notMissing(data[iter++])) ? "NULL" :data[iter-1].substring(0, indexOf(data[iter-1], '.')));
                cell.setLaunch(0);
                if(data[iter-1].indexOf("Released") != -1)
                {
                    data[iter-1] = data[iter-1].substring(indexOf(data[iter-1], "Released")+9);
                    try
                    {
                        sc = new Scanner(data[iter-1].replaceAll(",", ""));
                        cell.setLaunch(Integer.parseInt(sc.next()));
                    }catch (NumberFormatException e){
                        System.out.println("Released date existed but could not be parsed");
                    }
                }
            }
            case 4: {
                cell.setBody_dimensions((!isValid_notMissing(data[iter++].replace(",", ""))) ? "NULL" : data[iter-1]);
            }
            case 5:
            {
                try
                {
                    sc = new Scanner(data[iter++]);
                    if(sc.hasNext()) cell.setBody_weight(Float.parseFloat(sc.next()));
                    else cell.setBody_weight(0);
                } catch (NumberFormatException e)
                {
                    cell.setBody_weight(0);
                }
            }
            case 6: cell.setBody_sim((!isValid_notMissing(data[iter++])) ? "NULL" :data[iter-1]);
            case 7: cell.setDisplay_type((!isValid_notMissing(data[iter++])) ? "NULL" : data[iter-1]);
            case 8:
            {
                try
                {
                    sc = new Scanner(data[iter++]);
                    if(sc.hasNext()) cell.setDisplay_size(Float.parseFloat(sc.next()));
                    else cell.setDisplay_size(0);
                }catch (NumberFormatException e)
                {
                    cell.setDisplay_size(0);
                }
            }
            case 9: cell.setDisplay_resolution((!isValid_notMissing(data[iter++])) ? "NULL" : data[iter-1]);
            case 10: cell.setFeatures_sensors((!isValid_notMissing(data[iter++])) ? "NULL" : data[iter-1]);
            case 11: cell.setPlatform_os((!isValid_notMissing(data[iter])) ? "NULL" : data[iter].substring(0, indexOf(data[iter], ',')));
        }

        return cell;
    }

    public static float getAvgBodyWeight(LinkedList<Cell> cells)
    {
        float num = 0, sum = 0;
        for(Cell item: cells) if(item.getBody_weight() != 0)
        {
            sum += item.getBody_weight();
            num++;
        }
        return sum/num;
    }

    public static LinkedList<Cell> getAnnouncedNotEReleased(LinkedList<Cell> cells)
    {
        LinkedList<Cell> result = new LinkedList<>();
        for(Cell item : cells) if(item.getLaunch() != 0 && item.getLaunch() != item.getLaunch_announced()) result.add(item);
        return result;
    }

    static LinkedList<Cell> getPhoneWithNumFeatures(LinkedList<Cell> cells, int num)
    {
        LinkedList<Cell> result = new LinkedList<>();
        String temp;

        for(Cell item: cells)
        {
            if(item.getFeatures_sensors() == "NULL") continue;
            temp = item.getFeatures_sensors();
            if(temp.isEmpty() && num == 0)
            {
                result.add(item);
                continue;
            }
            int count = 1;
            for(char c: temp.toCharArray())
            {
                if(count > num) continue;
                if(c == ',') count++;
            }
            if(count == num) result.add(item);
        }

        return result;
    }

    public static LinkedList<Cell> getPhonesLaunchedSince(LinkedList<Cell> cells, int lowerLimit, int upperLimit)
    {
        LinkedList<Cell> result = new LinkedList<>();
        for (Cell item: cells) if(item.getLaunch() >= lowerLimit && item.getLaunch() <= upperLimit) result.push(item);
        return result;
    }

    public static int getYearMostLaunched(Map<String, LinkedList<Cell>> phonesByOEM, int lower, int upper)
    {
        Map<Integer, Integer> yearCount = new HashMap<>();
        int most = 0, year = 0;

        for(var pair : phonesByOEM.entrySet())
        {
            for(Cell item : pair.getValue())
            {
                if(item.getLaunch() == 0 || item.getLaunch() < lower || item.getLaunch() >= upper) continue;
                if(!yearCount.containsKey(item.getLaunch()))
                {
                    yearCount.put(item.getLaunch(), 1);
                }else
                {
                    yearCount.put(item.getLaunch(), yearCount.get(item.getLaunch())+1);
                }
            }
        }

        for (var pair: yearCount.entrySet())
        {
            if(pair.getValue() > most)
            {
                most = pair.getValue();
                year = pair.getKey();
            }
        }

        return year;

    }
}
