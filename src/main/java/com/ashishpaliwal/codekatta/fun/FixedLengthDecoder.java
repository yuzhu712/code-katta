package com.ashishpaliwal.codekatta.fun;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

/**
 * Fixed length decoder
 */
public abstract class FixedLengthDecoder<M> {

    private ByteBuffer messageBuffer;
    private int messageLength;

    public M decode(ByteBuffer byteBuffer) {

        // check for length
        if(messageBuffer == null) {
            messageLength = getLength(byteBuffer);
            messageBuffer = ByteBuffer.allocate(messageLength);
            if(byteBuffer.remaining() >= messageLength) {
                messageBuffer.put(byteBuffer.array(), 0, messageLength);
            }
        }


        return doDecode(messageBuffer);

    }

    protected abstract int getLength(ByteBuffer byteBuffer);

    protected abstract M doDecode(ByteBuffer messageBuffer);

    private void copyBuffers(ByteBuffer sourceBuffer, ByteBuffer destinationBuffer, int srcIndex, int length) {
        if(destinationBuffer.capacity() < length) {
            throw new BufferOverflowException();
        }
        sourceBuffer.position(srcIndex);
        for (int i = srcIndex; i < srcIndex + length; i++) {
             destinationBuffer.put(sourceBuffer.get());
        }
    }

}
