package com.conan.mvcdemo.controller;

import com.conan.mvcdemo.model.Course;
import com.conan.mvcdemo.services.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    private static Logger log = LoggerFactory.getLogger(CourseController.class);

    /**
     * 请求格式 /courses/view?courseId=111
     *
     * @param courseId 课程ID
     * @param model    Spring mvc 提供的特点的model
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewCourse(@RequestParam("courseId") Integer courseId, Model model) {
        Course course = courseService.getCoursebyId(courseId);

        log.debug("xxxxxx", courseId);

        model.addAttribute(course);
        return "course_overview";
    }

    /**
     * 请求格式 /courses/view2/courseId
     *
     * @param courseId
     * @param map      Spring  mvc model 的三种形式之一
     * @return
     */
    @RequestMapping(value = "/view2/{courseId}", method = RequestMethod.GET)
    public String viewCourse2(@PathVariable("courseId") Integer courseId, Map<String, Object> map) {
        Course course = courseService.getCoursebyId(courseId);
        log.debug("xxxxxx", courseId);
        map.put("course", course);
        return "course_overview";
    }

}
