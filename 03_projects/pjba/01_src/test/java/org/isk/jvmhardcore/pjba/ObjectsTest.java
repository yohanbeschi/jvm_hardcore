package org.isk.jvmhardcore.pjba;

import org.isk.jvmhardcore.pjba.object.first.User;
import org.isk.jvmhardcore.pjba.object.second.abs.Point;
import org.isk.jvmhardcore.pjba.object.second.conc.Point3D;
import org.isk.jvmhardcore.pjba.object.second.inter.IPoint;
import org.junit.Assert;
import org.junit.Test;

public class ObjectsTest {
  @Test
  public void simpleInstanciation() {
    final User user = new User();
    user.username = "JvmHardcore";
    user.age = 10;

    Assert.assertEquals("JvmHardcore", user.username);
    Assert.assertEquals(10, user.age);
  }

  @Test
  public void instanciationWithParameters() {
    final User user = new User("JvmHardcore", 10);

    Assert.assertEquals("JvmHardcore", user.username);
    Assert.assertEquals(10, user.age);
  }

  @Test
  public void settersAndGetters() {
    final User user = new User();
    user.setFieldInt(1);
    user.setFieldLong(2);
    user.setFieldFloat(3.3f);
    user.setFieldDouble(4.4);
    user.setFieldString("Five");

    Assert.assertEquals(1, user.getFieldInt());
    Assert.assertEquals(2, user.getFieldLong());
    Assert.assertEquals(3.3f, user.getFieldFloat(), 0.0001);
    Assert.assertEquals(4.4, user.getFieldDouble(), 0.0001);
    Assert.assertEquals("Five", user.getFieldString());
  }

  @Test
  public void factory() {
    final User user = User.factory();

    Assert.assertNotNull(user);

    user.username = "JvmHardcore";
    user.age = 10;

    Assert.assertEquals("JvmHardcore", user.username);
    Assert.assertEquals(10, user.age);
  }

  @Test
  public void implementsMethodFromAbstract() {
    final Point3D point3d = new Point3D(1, 2, 3);
    point3d.move(5, 10);

    Assert.assertEquals(6, point3d.x);
    Assert.assertEquals(12, point3d.y);
  }

  @Test
  public void implementsMethodFromInterface() {
    final IPoint ipoint = new Point3D(1, 2, 3);
    ipoint.goDown();

    final Point point3d = (Point) ipoint;
    Assert.assertEquals(1, point3d.x);
    Assert.assertEquals(1, point3d.y);
  }

  @Test
  public void callsOverloadedMethod() {
    final Point3D point3d = new Point3D(1, 2, 3);
    point3d.goDown(3, 6);

    Assert.assertEquals(4, point3d.x);
    Assert.assertEquals(8, point3d.y);
  }

  @Test
  public void callsMethodFromAbstract() {
    final Point point3d = new Point3D(1, 2, 3);
    point3d.goLeft();

    Assert.assertEquals(0, point3d.x);
    Assert.assertEquals(2, point3d.y);
  }

  @Test
  public void callsMethodExplicitlyFromAbstract() {
    final Point point3d = new Point3D(1, 2, 3);
    point3d.speed(10, 20);

    Assert.assertEquals(11, point3d.x);
    Assert.assertEquals(22, point3d.y);
  }

  @Test
  public void callsDelegateProtectedFromClass() {
    final Point3D point3d = new Point3D(1, 2, 3);
    point3d.goUp();

    Assert.assertEquals(1, point3d.x);
    Assert.assertEquals(3, point3d.y);
  }

  @Test
  public void callsDelegateProtectedFromParent() {
    final Point3D point3d = new Point3D(1, 2, 3);
    point3d.delegateGoRight();

    Assert.assertEquals(2, point3d.x);
    Assert.assertEquals(2, point3d.y);
  }

  @Test
  public void callsMethodFromInterface() {
    final Point3D point3d = new Point3D(1, 2, 3);
    Point3D.goDown(point3d);

    Assert.assertEquals(1, point3d.x);
    Assert.assertEquals(1, point3d.y);
  }

  @Test
  public void compareTo0() {
    final Point3D point3d = new Point3D(1, 2, 0);
    final int result = point3d.compareTo(null);

    Assert.assertEquals(-1, result);
  }

  @Test
  public void compareTo1() {
    final Point3D point3d = new Point3D(1, 2, 0);
    final int result = point3d.compareTo(new Integer(2));

    Assert.assertEquals(-1, result);
  }

  @Test
  public void compareTo2() {
    final Point3D point3d1 = new Point3D(1, 2, 0);
    final Point3D point3d2 = new Point3D(1, 2, 0);
    final int result = point3d1.compareTo(point3d2);

    Assert.assertEquals(0, result);
  }

  @Test
  public void compareTo3() {
    final Point3D point3d1 = new Point3D(1, 2, 0);
    final int result = point3d1.compareTo(point3d1);

    Assert.assertEquals(0, result);
  }

  @Test
  public void compareTo4() {
    final Point3D point3d1 = new Point3D(1, 2, 0);
    final Point3D point3d2 = new Point3D(2, 2, 0);
    final int result = point3d1.compareTo(point3d2);

    Assert.assertEquals(-1, result);
  }

  @Test
  public void compareTo5() {
    final Point3D point3d1 = new Point3D(1, 2, 0);
    final Point3D point3d2 = new Point3D(1, 3, 0);
    final int result = point3d1.compareTo(point3d2);

    Assert.assertEquals(-1, result);
  }

  @Test
  public void compareTo6() {
    final Point3D point3d1 = new Point3D(1, 2, 0);
    final Point3D point3d2 = new Point3D(0, 2, 0);
    final int result = point3d1.compareTo(point3d2);

    Assert.assertEquals(1, result);
  }

  @Test
  public void compareTo7() {
    final Point3D point3d1 = new Point3D(1, 2, 0);
    final Point3D point3d2 = new Point3D(1, 1, 0);
    final int result = point3d1.compareTo(point3d2);

    Assert.assertEquals(1, result);
  }
}
