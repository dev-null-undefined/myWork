# USAGE
# python pi_surveillance.py --conf conf.json

# import the necessary packages
from picamera.array import PiRGBArray
from picamera import PiCamera
import datetime
import imutils
import time
import cv2

client = None


# initialize the camera and grab a reference to the raw camera capture
camera = PiCamera()
camera.resolution = (800,400)
camera.framerate = 24
rawCapture = PiRGBArray(camera, size=(800,400))

# allow the camera to warmup, then initialize the average frame, last
# uploaded timestamp, and frame motion counter
print("[INFO] warming up...")
time.sleep(2)
lastUploaded = datetime.datetime.now()
motionCounter = 0
lastFrame = None
# capture frames from the camera
for f in camera.capture_continuous(rawCapture, format="bgr", use_video_port=True):
	# grab the raw NumPy array representing the image and initialize
	# the timestamp and occupied/unoccupied text
    frame = f.array
    if(lastFrame!=None):
        timestamp = datetime.datetime.now()
        text = "Unoccupied"

        difference=cv2.absdiff(lastFrame,frame)
        gray = cv2.cvtColor(difference, cv2.COLOR_BGR2GRAY)
        blur = cv2.GaussianBlur(gray, (5,5), 0)
        # threshold the delta image, dilate the thresholded image to fill
        # in holes, then find contours on thresholded image
        thresh = cv2.threshold(blur, 5, 255, cv2.THRESH_BINARY)[1]
        thresh = cv2.dilate(thresh, None, iterations=2)
        cnts = cv2.findContours(thresh.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
        cnts = imutils.grab_contours(cnts)

	    # loop over the contours
        for c in cnts:
            if cv2.contourArea(c) < 100:
                continue
            (x, y, w, h) = cv2.boundingRect(c)
            cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 255, 0), 2)
            text = "Occupied"

        # draw the text and timestamp on the frame
        ts = timestamp.strftime("%A %d %B %Y %I:%M:%S%p")
        cv2.putText(frame, "Room Status: {}".format(text), (10, 20), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 255), 2)
        cv2.putText(frame, ts, (10, frame.shape[0] - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.35, (0, 0, 255), 1)

        # if text == "Occupied":
        # 	if (timestamp - lastUploaded).seconds >= conf["min_upload_seconds"]:
        # 		# increment the motion counter
        # 		motionCounter += 1

        # 		# check to see if the number of frames with consistent motion is
        # 		# high enough
        # 		if motionCounter >= conf["min_motion_frames"]:
        # 			# check to see if dropbox sohuld be used
        # 			if conf["use_dropbox"]:
        # 				# write the image to temporary file
        # 				t = TempImage()
        # 				cv2.imwrite(t.path, frame)

        # 				# upload the image to Dropbox and cleanup the tempory image
        # 				print("[UPLOAD] {}".format(ts))
        # 				path = "/{base_path}/{timestamp}.jpg".format(
        # 				    base_path=conf["dropbox_base_path"], timestamp=ts)
        # 				client.files_upload(open(t.path, "rb").read(), path)
        # 				t.cleanup()

        # 			# update the last uploaded timestamp and reset the motion
        # 			# counter
        # 			lastUploaded = timestamp
        # 			motionCounter = 0

        # otherwise, the room is not occupied
        # else:
        # 	motionCounter = 0

        # check to see if the frames should be displayed to screen
        if True:
            # display the security feed
            cv2.imshow("Security Feed", frame)
            key = cv2.waitKey(1) & 0xFF

            # if the `q` key is pressed, break from the lop
            if key == ord("q"):
                break

        # clear the stream in preparation for the next frame
        lastFrame=frame
        rawCapture.truncate(0)