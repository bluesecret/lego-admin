taskkill /fi "windowtitle eq LegoAdmin"  /f /t

@echo off
set file_path=lego-parent\lego-admin\target\lego-admin.jar
if exist %file_path% (
    cd lego-parent\lego-admin\target
    start "LegoAdmin" java -jar -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=logs\oom_dump.log -Xms500M -Xmx500M lego-admin.jar
    echo LegoAdmin�����ɹ���
) else (
    echo δ�ҵ�������lego-admin.jar����ȷ���Ƿ����ɹ���
)
pause