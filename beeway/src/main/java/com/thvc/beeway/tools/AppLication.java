package com.thvc.beeway.tools;

import java.io.File;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class AppLication extends Application {
	private File cacheDir;
	private ImageLoaderConfiguration config;

	@Override
	public void onCreate() {
		 super.onCreate();
		cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "kmq/ImgCache");
		config = new ImageLoaderConfiguration.Builder(getApplicationContext())
				.memoryCacheExtraOptions(320, 480)
				// max width, max height，即保存的每个缓存文件的最大长宽
				.threadPoolSize(3)
				// 线程池内加载的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				// .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 *
				// 1024)) // You can pass your own memory cache
				// implementation/你可以通过自己的内存缓存实现
				.memoryCacheSize(2 * 1024 * 1024).diskCacheSize(50 * 1024 * 1024).tasksProcessingOrder(QueueProcessingType.LIFO)
				.diskCacheFileCount(100)
				// 缓存的文件数量
				.diskCache(new UnlimitedDiscCache(cacheDir))
				// 自定义缓存路径
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				// 将保存的时候的URI名称用MD5 加密
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout
				// 启用软引用缓存
				.memoryCache(new WeakMemoryCache())
				// 是否打印日志用于检查错误
				.writeDebugLogs().build();// 开始构建
		ImageLoader.getInstance().init(config);
	}
}
