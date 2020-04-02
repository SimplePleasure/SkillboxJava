package myBeanApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Car {

    @Autowired
    @Qualifier("engine")
    Detail engine;
    @Autowired
    @Qualifier("wheel")
    Detail wheels;
    @Autowired
    @Qualifier("transmission")
    Detail transmission;

//    Car ( @Qualifier("engine") Detail engine,
//         @Qualifier("transmission") Detail transmission,
//         @Qualifier("wheel") Detail wheel ) {
//                this.engine = engine;
//                this.transmission = transmission;
//                this.wheels = wheel;
//    }


    @Override
    public String toString() {
        return engine.detail() + wheels.detail() + transmission.detail();
    }




}
