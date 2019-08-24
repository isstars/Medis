package myRedis.commands;

import myRedis.Command;
import myRedis.Database;
import myRedis.Protocol;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class LPUSHCommand implements Command {
    private List<Object> args;
    @Override
    public void setArgs(List<Object> args) {
        this.args = args;
    }

    @Override
    public void run(OutputStream os) throws IOException {
        if (args.size() != 2){
            Protocol.writeError(os,"the size should be two");
            return;
        }
        String key = new String((byte[])args.get(0));
        String value = new String((byte[])args.get(1));
        List<String> list = Database.getList(key);
        //头插法将value数据插入list中
        list.add(0,value);
        Protocol.writeInteger(os,list.size());
    }
}
