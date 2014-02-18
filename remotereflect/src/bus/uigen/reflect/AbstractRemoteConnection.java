package bus.uigen.reflect;

import bus.uigen.reflect.remote.RemoteConnection;

public abstract class AbstractRemoteConnection implements RemoteConnection {

	

	@Override
	public void disconnect(Object theFactoryName) {
		RemoteSelector.unRegister(theFactoryName);

	}

}
