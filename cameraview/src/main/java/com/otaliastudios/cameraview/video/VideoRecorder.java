package com.otaliastudios.cameraview.video;

import com.otaliastudios.cameraview.VideoResult;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

/**
 * Interface for video recording.
 * Don't call start if already started. Don't call stop if already stopped.
 */
public abstract class VideoRecorder {

    /**
     * Listens for video recorder events.
     */
    public interface VideoResultListener {

        /**
         * The operation was completed, either with success or with an error.
         * @param result the result or null if error
         * @param exception the error or null if everything went fine
         */
        void onVideoResult(@Nullable VideoResult.Stub result, @Nullable Exception exception);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED) VideoResult.Stub mResult;
    @VisibleForTesting final VideoResultListener mListener;
    @SuppressWarnings("WeakerAccess")
    protected Exception mError;
    private boolean mIsRecording;

    /**
     * Creates a new video recorder.
     * @param listener a listener
     */
    VideoRecorder(@Nullable VideoResultListener listener) {
        mListener = listener;
    }

    /**
     * Starts recording a video.
     *
     * @param stub the video stub
     */
    public final void start(@NonNull VideoResult.Stub stub) {
        mResult = stub;
        mIsRecording = true;
        onStart();
    }

    /**
     * Stops recording.
     */
    public final void stop() {
        onStop();
    }

    /**
     * Returns true if it is currently recording.
     * @return true if recording
     */
    public boolean isRecording() {
        return mIsRecording;
    }

    protected abstract void onStart();

    protected abstract void onStop();

    /**
     * Subclasses can call this to notify that the result was obtained,
     * either with some error (null result) or with the actual stub, filled.
     */
    @SuppressWarnings("WeakerAccess")
    @CallSuper
    protected void dispatchResult() {
        mIsRecording = false;
        if (mListener != null) {
            mListener.onVideoResult(mResult, mError);
        }
        mResult = null;
        mError = null;
    }
}
