package cn.itcast.ssm.dao;

import java.util.List;

import cn.itcast.ssm.pojo.Say;
import cn.itcast.ssm.pojo.Tip;
import cn.itcast.ssm.pojo.TipUser;
import cn.itcast.ssm.pojo.User;

public interface SayDao {
	//���û������۲���say����
	public void say(Say say) throws Exception;
	
	//�鿴���е����ۣ�����tipid
	public List<Say> getAllSay(int tipid) throws Exception;
	
}
