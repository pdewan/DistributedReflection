package bus.uigen.reflect.remote;

public interface RemoteConnection {
	void connect(Object theFactoryName);
	void disconnect(Object theFactoryName);

}
