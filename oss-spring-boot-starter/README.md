### 配置使用哪种对象存储
目前支持以下三种类型的对象存储
MINIO
ALIYUN
AMAZON_S3
```yaml
oss:
  enabled: true
  type: MINIO # ALIYUN / AMAZON_S3 目前支持三种类型
  endpoint: http://127.0.0.1:9000 # minio 服务地址
  bucket: demo # 桶名称
  accessKey: xxxxxx
  secretKey: xxxxxxxxxxx
  region: tianjin
```

### 对于批量上传，支持批量上传后续处理
需要创建名为"ossBatchUploadEventListener"的Bean,作为ApplicationEvent的观察者，
自定义上传成功之后的逻辑；event.getUploadedFiles()能拿到上传成功的文件名，如果没有
则执行默认逻辑
```yaml
@Slf4j
@Component
public class OssBatchUploadEventListener {

    @EventListener
    public void handleOssUploadEvent(OssBatchUploadEvent event) {
        // 获取上传成功的文件名列表
        List<String> uploadedFiles = event.getUploadedFiles();

        // 默认处理逻辑
        for (String fileName : uploadedFiles) {
            log.info("111upload success file: {}", fileName);
        }
    }
}
```