package com.conan.mvcdemo.services;

import com.conan.mvcdemo.model.Course;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {

    Course getCoursebyId(Integer courseId);
}
