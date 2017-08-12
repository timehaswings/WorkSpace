package com.example.data;

import android.os.Parcel;
import android.os.Parcelable;

public class MediaInfo implements Parcelable{
	
	private int id;
	private String title,desc,size,src;
	private int totalTime,cTime;
	private boolean isPlaying;
	
	public MediaInfo(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public boolean isPlaying() {
		return isPlaying;
	}
	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	public int getcTime() {
		return cTime;
	}
	public void setcTime(int cTime) {
		this.cTime = cTime;
	}

	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(totalTime);
		dest.writeInt(cTime);
		dest.writeString(title);
		dest.writeString(desc);
		dest.writeString(size);
		dest.writeString(src);
		dest.writeInt(isPlaying ? 1:0);
	}
	
    public static final Parcelable.Creator<MediaInfo> CREATOR
            = new Parcelable.Creator<MediaInfo>() {
        public MediaInfo createFromParcel(Parcel in) {
            return new MediaInfo(in);
        }

        public MediaInfo[] newArray(int size) {
            return new MediaInfo[size];
        }
    };
    
    private MediaInfo(Parcel in) {
        id = in.readInt();
        totalTime=in.readInt();
        cTime=in.readInt();
        title=in.readString();
        desc=in.readString();
        size=in.readString();
        src=in.readString();
        isPlaying = (in.readInt()==1 ? true : false);
    }

}
