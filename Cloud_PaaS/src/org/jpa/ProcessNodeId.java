package org.jpa;

import java.io.Serializable;
import java.util.Objects;


public class ProcessNodeId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long nodeId;
    private Long processId;

    public ProcessNodeId() {
    }

    public ProcessNodeId(Long nodeId, Long processId) {
        this.nodeId = nodeId;
        this.processId = processId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessNodeId that = (ProcessNodeId) o;
        return Objects.equals(nodeId, that.nodeId) && 
               Objects.equals(processId, that.processId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, processId);
    }
}