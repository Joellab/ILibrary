import gym
import time
import sys
import numpy as np

num_episodes = 5000
max_number_of_steps = 10
goal_average_steps = 70 #Target Score
num_consecutive_iterations = 100
last_time_steps = np.zeros(num_consecutive_iterations)
env = gym.make('GridWorld-v1')

# create a q_table
q_table = np.random.uniform(low=-1, high=1, size=(4 * 4, 4))

# get next action from next_state
def get_action(state, action, observation, reward, episode, epsilon_coefficient=0.0):
    next_state = observation
    epsilon = (0.99 ** episode) * epsilon_coefficient  #ε
    if epsilon <= np.random.uniform(0, 1):
        next_action = np.argmax(q_table[next_state])
    else:
        next_action = np.random.choice([0, 1, 2, 3])

    alpha = 0.2  # learning rate α
    gamma = 0.99  # γ
    q_table[state, action] = (1 - alpha) * q_table[state, action] + alpha * (
            reward + gamma * q_table[next_state, next_action]) # update q_table

    return next_action, next_state


timer = time.time()
for episode in range(num_episodes):
    env.reset()
    episode_reward = 0
    q_table_cache = q_table

    for t in range(max_number_of_steps):

        env.render()
        state = env.state
        action = np.argmax(q_table[state])
        observation, reward, done, info = env.step(action)
        action, state = get_action(state, action, observation, reward, episode, 0.5)
        episode_reward += reward
        if done:
            np.savetxt("q_table.txt", q_table, delimiter=",")
            print('finished %d times training，this train has %d steps。episode_reward：%d，average： %f' % (episode, t + 1, reward, last_time_steps.mean()))
            last_time_steps = np.hstack((last_time_steps[1:], [reward]))    
            break
    q_table = q_table_cache

    episode_reward = -100
    print('finished %d times trade, t +ining，this train has %d steps。episode_reward：%d，average： %f' % (episode, t + 1, reward, last_time_steps.mean()))
    last_time_steps = np.hstack((last_time_steps[1:], [reward])) 

    if (last_time_steps.mean() >= goal_average_steps):
        np.savetxt("q_table.txt", q_table, delimiter=",")
        print('use %d s,after training %d times，The model reaches the test standard!' % (time.time() - timer, episode))

        env.close()

        sys.exit()


env.close()
sys.exit()
