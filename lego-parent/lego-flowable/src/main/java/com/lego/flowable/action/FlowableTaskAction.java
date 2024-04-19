package com.lego.flowable.action;

import com.lego.core.action.MaintainAction;
import com.lego.core.data.ActionType;
import com.lego.core.exception.BusinessException;
import com.lego.core.util.EntityUtil;
import com.lego.core.web.LegoBeanFactory;
import com.lego.flowable.assembler.FlowableModelAssembler;
import com.lego.system.dao.ISysEmployeeDao;
import com.lego.system.entity.SysEmployee;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;

import java.text.MessageFormat;
import java.util.List;

public abstract class FlowableTaskAction extends MaintainAction {

    private String taskId;
    protected Task task;

    protected ISysEmployeeDao employeeDao = getDao(ISysEmployeeDao.class);
    protected TaskService taskService = LegoBeanFactory.getBean(TaskService.class);
    protected RuntimeService runtimeService = LegoBeanFactory.getBean(RuntimeService.class);
    protected HistoryService historyService = LegoBeanFactory.getBean(HistoryService.class);
    protected RepositoryService repositoryService = LegoBeanFactory.getBean(RepositoryService.class);

    protected FlowableModelAssembler modelAssembler = LegoBeanFactory.getBean(FlowableModelAssembler.class);

    protected FlowableTaskAction(String permissionCode, String operatorCode, String taskId) {
        super(permissionCode, operatorCode);
        this.taskId = taskId;
    }

    @Override
    protected void preprocess() {
        SysEmployee employee = employeeDao.findByCode(operatorCode);
        TaskQuery taskQuery = taskService.createTaskQuery().active().taskId(taskId);
        if (!employee.isAdmin()) {
            List<String> candidateGroups = EntityUtil.getCode(employee.getRoles());
            candidateGroups.add(EntityUtil.getCode(employee.getDept()));
            taskQuery.taskCandidateOrAssigned(operatorCode).taskCandidateGroupIn(candidateGroups);
        }
        this.task = taskQuery.singleResult();
        BusinessException.check(this.task != null, "当前任务审核人非[{0}]，审核失败！", operatorCode);
    }

    @Override
    protected ActionType getActionType() {
        return ActionType.MODIFY;
    }

    @Override
    protected String getEntityName() {
        return MessageFormat.format("任务[{0}]", task.getName());
    }
}
