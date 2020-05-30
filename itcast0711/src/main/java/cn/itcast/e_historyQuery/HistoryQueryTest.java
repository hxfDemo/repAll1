package cn.itcast.e_historyQuery;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class HistoryQueryTest {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	/**查询历史流程实例*/
	@Test
	public void findHistoryProcessInstance(){
		String processInstanceId = "6101";
//		HistoricProcessInstance hpi = processEngine.getHistoryService()//与历史数据（历史表）相关的Service
//						.createHistoricProcessInstanceQuery()//创建历史流程实例查询
//						.processInstanceId(processInstanceId)//使用流程实例ID查询
//						.orderByProcessInstanceStartTime().asc()
//						.singleResult();
//		System.out.println(hpi.getId()+"    "+hpi.getProcessDefinitionId()+"    "+hpi.getStartTime()+"    "+hpi.getEndTime()+"     "+hpi.getDurationInMillis());
		HistoricProcessInstance singleResult = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).orderByProcessInstanceStartTime().asc().singleResult();
		if (singleResult!=null){
			System.out.println(singleResult.getStartTime()+"----"+singleResult.getProcessDefinitionId());
		}
	}
	
	/**查询历史活动*/
	@Test
	public void findHistoryActiviti(){
			String processInstanceId = "1401";
//		List<HistoricActivityInstance> list = processEngine.getHistoryService()//
//						.createHistoricActivityInstanceQuery()//创建历史活动实例的查询
//						.processInstanceId(processInstanceId)//
//						.orderByHistoricActivityInstanceStartTime().asc()//
//						.list();
//		if(list!=null && list.size()>0){
//			for(HistoricActivityInstance hai:list){
//				System.out.println(hai.getId()+"   "+hai.getProcessInstanceId()+"   "+hai.getActivityType()+"  "+hai.getStartTime()+"   "+hai.getEndTime()+"   "+hai.getDurationInMillis());
//				System.out.println("#####################");
//			}
//		}
		List<HistoricActivityInstance> list = processEngine.getHistoryService().createHistoricActivityInstanceQuery().processDefinitionId(processInstanceId).list();
		for (HistoricActivityInstance his : list) {
			System.out.println(his.getProcessDefinitionId()+"+++++"+his.getStartTime());
		}
	}
	
	/**查询历史任务*/
	@Test
	public void findHistoryTask(){
		String processInstanceId = "1401";
//		List<HistoricTaskInstance> list = processEngine.getHistoryService()//与历史数据（历史表）相关的Service
//						.createHistoricTaskInstanceQuery()//创建历史任务实例查询
//						.processInstanceId(processInstanceId)//
//						.orderByHistoricTaskInstanceStartTime().asc()
//						.list();

		List<HistoricTaskInstance>  list= processEngine.getHistoryService().createHistoricTaskInstanceQuery().orderByHistoricTaskInstanceStartTime().asc().list();

		if(list!=null && list.size()>0){
			for(HistoricTaskInstance hti:list){
				System.out.println(hti.getId()+"    "+hti.getName()+"    "+hti.getProcessInstanceId()+"   "+hti.getStartTime()+"   "+hti.getEndTime()+"   "+hti.getDurationInMillis());
				System.out.println("################################");
			}
		}
	}
	
	/**查询历史流程变量*/
	@Test
	public void findHistoryProcessVariables(){
		String processInstanceId = "201";
//		List<HistoricVariableInstance> list = processEngine.getHistoryService()//
//						.createHistoricVariableInstanceQuery()//创建一个历史的流程变量查询对象
//						.processInstanceId(processInstanceId)//
//						.list();
		List<HistoricVariableInstance> list = processEngine.getHistoryService().createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).orderByProcessInstanceId().asc().list();

		if(list!=null && list.size()>0){
			for(HistoricVariableInstance hvi:list){
				System.out.println(hvi.getId()+"   "+hvi.getProcessInstanceId()+"   "+hvi.getVariableName()+"   "+hvi.getVariableTypeName()+"    "+hvi.getValue());
				System.out.println("###############################################");
			}
		}
	}
	
}
