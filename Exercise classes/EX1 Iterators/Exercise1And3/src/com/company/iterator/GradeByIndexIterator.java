package com.company.iterator;

import com.company.Student;

public class GradeByIndexIterator extends BasicIterator {

    private final Student[] arrayOfStudents;
    private final int requiredIndex;

    public GradeByIndexIterator(Student[] arrayOfStudents, int requiredIndex, int grade) {
        super(arrayOfStudents);
        this.arrayOfStudents = arrayOfStudents;
        this.requiredIndex = requiredIndex;
        findIndex();
        setAGrade(grade);
    }

    public void findIndex(){
        while(arrayOfStudents[pos].getIndexNumber() != requiredIndex) next();
    }

    public void setAGrade(int grade){
        arrayOfStudents[pos].setSubjectGrade(grade);
    }

}
