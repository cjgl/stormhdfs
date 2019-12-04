package cn.fy;

import org.apache.storm.shade.org.apache.commons.lang.StringUtils;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.*;


/**
 * 产生词频样本的数据源
 */
public class DataSourceSpout extends BaseRichSpout {

    private List<String> list = Arrays.asList("Spark", "Hadoop", "HBase", "Storm", "Flink", "Hive");

    private SpoutOutputCollector spoutOutputCollector;

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
    }

    public void nextTuple() {
        // 模拟产生数据
        String lineData = productData();
        spoutOutputCollector.emit(new Values(lineData));   //向BOLT 提交信息
        Utils.sleep(1000);
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));                            //spout   象征性ID
    }


    /**
     * 模拟数据
     */
    private String productData() {
        Collections.shuffle(list);           //打乱顺序重新排序
        Random random = new Random();              //声明一个随机数的对象
        int endIndex = random.nextInt(list.size()) % (list.size()) + 1;   //取随机数
        return StringUtils.join(list.toArray(), "\t", 0, endIndex);    //返回   0  ---n  长度的 数组的值  
    }

}
