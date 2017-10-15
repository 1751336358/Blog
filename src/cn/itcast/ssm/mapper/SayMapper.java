package cn.itcast.ssm.mapper;

import java.util.List;

import cn.itcast.ssm.pojo.Say;
import cn.itcast.ssm.pojo.Tip;
import cn.itcast.ssm.pojo.TipUser;
import cn.itcast.ssm.pojo.User;

public interface SayMapper {
	
	//���û������۲���say����
	public void say(Say say) throws Exception;
	
	//�鿴���е����ۣ�����tipid
	public List<Say> getAllSay(int tipid) throws Exception;
	
	// ɾ�����ۣ�����tipid�������ߵ�id����������detail
	public void delSay(Say say) throws Exception;
	
}
