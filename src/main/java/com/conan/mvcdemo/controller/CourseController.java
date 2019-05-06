package com.conan.mvcdemo.controller;

import com.conan.mvcdemo.model.Course;
import com.conan.mvcdemo.services.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/view3", method = RequestMethod.GET)
    public String viewCourse2(HttpServletRequest httpServletRequest) {
        Integer courseId = Integer.valueOf(httpServletRequest.getParameter("courseId"));
        Course course = courseService.getCoursebyId(courseId);
        log.debug("xxxxxx", courseId);
        httpServletRequest.setAttribute("course", course);
        return "course_overview";
    }

    /**
     * 直接打开一个节目
     *
     * @return
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET, params = "add")
    public String createCourse() {
        return "course_admin/edit";
    }

    /**
     * 保存之后 查询课程
     *
     * @param course
     * @return
     */
   /* @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String doSave1(Course course) {
        course.setCourseId(122);
        return "redirect:view2/" + course.getCourseId();
    }*/
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String doSave(@ModelAttribute Course course) {
        course.setCourseId(122);
        return "redirect:view2/" + course.getCourseId();
    }

}
