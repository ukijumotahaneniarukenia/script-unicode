package app;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.UnicodeSet;
import sun.misc.Signal;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

    private static final String PROGRAM_NAME = "uni-detect-lang-info";
    private static final String PROGRAM_VERSION = "-1-0-0";

    private static final String PROGRAM_CMD = "java -jar";
    private static final String PROGRAM_SUFFIX = "-SNAPSHOT-jar-with-dependencies.jar";

    private static final String SIGNAL_HANDLE_INT = "INT";

    private static final String SPLITTER = "";
    private static final String SEPARATOR = " ";
    private static final String FS = "\t";
    private static final String RS = "\n";

    private static final String CODE_POINT_START = "codePointStart";
    private static final String CODE_POINT_END = "codePointEnd";

    private static final String OPTION_USAGE_UNICODE = "\'ￆￋ\uFFBFￌＷｗ：ￕｰ｣￤ﾠｳ･ﾊｹ０Ｅ\uFFD1ｖ：ￍￕｉ\uFFD0ﾏＭﾋﾪ､\'";

    private static void usage(){

        System.out.println("Usageだよーん" +
                RS +
                RS +
                "echo "+ OPTION_USAGE_UNICODE + "| grep -Po . | xargs -n4 |" + PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX +
                RS +
                RS +
                "echo "+ OPTION_USAGE_UNICODE + "| grep -Po . |" + PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX +
                RS +
                RS +
                "echo "+ OPTION_USAGE_UNICODE + "|" + PROGRAM_CMD + SEPARATOR + PROGRAM_NAME + PROGRAM_VERSION + PROGRAM_SUFFIX +
                RS
        );
        System.exit(0);
    }
    private static void trap(List<String> liz){
        for (String ele:liz) {
            catch_sig(ele);
        }
    }
    private static void catch_sig(String str) {
        Signal sig = new Signal(str);
        Signal.handle(sig, Signal -> {
            if (SIGNAL_HANDLE_INT.equals(sig.getName())) {
                usage();
            }
        });
    }
    private static List<String> pre_process(Scanner stdin){
        List<String> rt = new ArrayList<>();
        while (stdin.hasNextLine()) {
            rt.add(stdin.nextLine());
        }
        stdin.close();
        return rt;
    }
    private static Map<Integer, List<String>> sub_process(List<String> liz){
        Map<Integer, List<String>> rt= new LinkedHashMap<>();

        for (int i = 0 ; i<liz.size();i++){

            List<String> list = null;

            list = new ArrayList<>(Arrays.asList(liz.get(i).split(SEPARATOR)));

            if(list.size() == 1){
                //割れない場合はグラム化
                list = new ArrayList<>(Arrays.asList(liz.get(i).split(SPLITTER)));
            }

            rt.put( i + 1 ,list);
        }
        return rt;
    }

    private static Map<Integer,Map<String,Integer>> grpMinMax(List<Integer> list){

        Map<Integer,Map<String,Integer>> rt = new LinkedHashMap<>();

        int cnt = list.size();

        Map<Integer,List<Integer>> map = IntStream.range(0,cnt).boxed().collect(Collectors.groupingBy(idx->list.get(idx)-idx,Collectors.mapping(idx->list.get(idx),Collectors.toList())));

        int idx = 0;

        for (Map.Entry<Integer,List<Integer>> entry : map.entrySet()){

            Map<String,Integer> m = new LinkedHashMap<>();
            List<Integer> l = new LinkedList<>();

            idx++;

            m.put(CODE_POINT_START,entry.getValue().stream().min(Integer::compareTo).get());
            m.put(CODE_POINT_END,entry.getValue().stream().max(Integer::compareTo).get());

            rt.put(idx,m);
        }

        return rt;
    }

    private static List<Map<String,String>> wrapperUnicodeGraphDetailInfo(List<String> graphList){

        List<Map<String,String>> rt = new LinkedList<>();

        List<Integer> codepointList = graphList.stream().map(graph->UCharacter.getCodePoint(graph.toCharArray()[0])).collect(Collectors.toSet()).stream().sorted().collect(Collectors.toList());

        Map<Integer,Map<String,Integer>> map = grpMinMax(codepointList);

        Map<Integer,Map<String,Integer>> sortMap = new TreeMap<>(map);

        for(Map.Entry<Integer,Map<String,Integer>> entry : sortMap.entrySet()){

            StringBuffer sb = new StringBuffer();

            UnicodeSet unicodeSet = new UnicodeSet();

            unicodeSet.set(entry.getValue().get(CODE_POINT_START),entry.getValue().get(CODE_POINT_END));

            unicodeSet._generatePattern(sb,true);

            {
                System.out.print(entry.getKey());
                System.out.print(FS);
                System.out.print(entry.getValue());
                System.out.print(FS);
                System.out.print(sb.toString());
            }
            System.out.println();
        }

        return rt;
    }

    public static void main(String... cmdLineArgs) {

        trap(new ArrayList<>(Arrays.asList(SIGNAL_HANDLE_INT)));

        if(cmdLineArgs.length > 0){

            usage();

        }else{

            Map<Integer, List<String>> map = sub_process(pre_process(new Scanner(System.in)));

            if(map.size() == 0){
                usage();
            }

            for(Map.Entry<Integer,List<String>> entry : map.entrySet()){

                wrapperUnicodeGraphDetailInfo(entry.getValue());


            }
        }
    }
}