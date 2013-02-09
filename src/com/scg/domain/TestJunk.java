package com.scg.domain;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 1/14/13
 * Time: 8:14 PM
 */
public class TestJunk {

    public class Foo {
        private int value;

        public Foo(final int value) {
            this.value = value;
        }
        public class Bar {
            public void process() {
                System.out.println(value);
            }
        }
    }

}
