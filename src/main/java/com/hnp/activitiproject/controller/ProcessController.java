package com.hnp.activitiproject.controller;

import com.hnp.activitiproject.entity.User;
import com.hnp.activitiproject.repository.UserRepository;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProcessController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  ProcessRuntime processRuntime;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @RolesAllowed({ "ROLE_ACTIVITI_USER", "ROLE_ACTIVITI_ADMIN" })
    @GetMapping("/start")
    public void startProcess() {

        System.out.println("in start method...");

        User adminUser = this.userRepository.findUserByUsername("admin").get();
        Map<String, Object> variables = new HashMap<>();
        variables.put("user", adminUser);

//        //Deploy the process definition
//        repositoryService.createDeployment()
//                .addClasspathResource("processes/simpleprocess.bpmn20.xml")
//                .deploy();
//        System.out.println("Your process should be deployed...");

        runtimeService.startProcessInstanceByKey("simple-process", variables);

//        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 1));

//        System.out.println("*********> Available Process definitions: \" + processDefinitionPage.getTotalItems()");
//        for (ProcessDefinition pd : processDefinitionPage.getContent()) {
//            System.out.println("*******************\t > Process definition: " + pd);
//        }


    }

    @GetMapping("/test-log")
    public Long test() {
        return runtimeService.createProcessInstanceQuery().count();
    }
}
