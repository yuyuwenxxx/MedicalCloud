package org.jpa;

import java.util.List;
import javax.ejb.Local;

@Local
public interface ProcessNodeFacadeLocal {

    /**
     * 保存新的流程节点
     */
	public void save(ProcessNode entity);

    /**
     * 删除流程节点
     */
	public void delete(ProcessNode entity);

    /**
     * 更新流程节点
     */
	public ProcessNode update(ProcessNode entity);

    /**
     * 根据复合主键查找
     */
	public ProcessNode findById(Long nodeId, Long processId);

    /**
     * 根据流程ID查找所有节点
     */
	public List<ProcessNode> findByProcessId(Long processId);

    /**
     * 查找所有流程节点
     */
	public List<ProcessNode> findAll();

    /**
     * 获取指定流程的最大节点ID（用于生成新节点ID）
     */
	public Long getMaxNodeId(Long processId);

    /**
     * 根据流程ID获取当前节点
     */
	public ProcessNode findCurrentNode(Long processId);

    /**
     * 根据流程ID获取最新节点
     */
	public ProcessNode findLatestNode(Long processId);
}