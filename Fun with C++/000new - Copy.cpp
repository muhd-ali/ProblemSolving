#include <stdio.h>
#include <conio.h>
#include <iostream>
#include "C:\Users\Ali\Downloads\glut36\glut-3.6\include\GL\glut.h"
#include <stdlib.h>

#define WINW 1000
#define WINL 1000

void setup()
{
 glClearColor(0.0f,0.0f,0.0f,0.0f);
 glClear(GL_COLOR_BUFFER_BIT);
 glutSwapBuffers();
}

float xmap(int x)
{
 float fx,halfw;
 halfw=(float)WINW/2;
 fx=x/halfw;
 return fx;
}

float ymap(int y)
{
 float fy,halfl;
 halfl=(float)WINL/2;
 fy= y/halfl;
 return fy;
}


void display()
{
 int i;
 for(i=1;i<=4;i++)
 {
  glBegin(GL_LINE_STRIP);
        glColor3f(0.0f,1.0f,0.0f);
   glVertex3f(xmap(0),ymap(0),0.0f);
            glVertex3f(xmap(50),ymap(25),0.0f);
   glVertex3f(xmap(50),ymap(0),0.0f);
  glEnd();
  glutSwapBuffers();
  glTranslatef(xmap(50),0.0f,0.0f);
 }
}

int main(int argc,char * argv[])
{
 glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
 glutInitWindowSize(WINW,WINL);
 glutCreateWindow("123techguide.blogspot.com");

 setup();
 glutDisplayFunc(display);

 glutMainLoop();

 getch();

 return 0;
}
