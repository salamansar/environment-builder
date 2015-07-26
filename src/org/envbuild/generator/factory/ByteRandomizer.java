package org.envbuild.generator.factory;

/**
 *
 * @author kovlyashenko
 */
public class ByteRandomizer extends AbstractValueRandomizer<Byte> {

    @Override
    public Byte generateValue() {
        return new Integer(getRandom().nextInt(100)).byteValue();
    }

}
