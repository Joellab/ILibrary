import random
import gym
import numpy as np
import time
import sys


class GridEnv1(gym.Env):
    metadata = {
        'render.modes': ['human', 'rgb_array'],
        'video.frames_per_second': 2
    }

    def __init__(self):

        self.states = list(range(16))  # state

        self.Terminal = 15 # goal
        self.obs1 = 11 # static obs
        self.obs2 = 13 # dynamic obs
        self.obs3 = 4  # dynamic obs
        self.states.remove(self.obs1)
        self.states.remove(self.obs2)
        self.states.remove(self.obs3)
        self.obs2Move = 1 # action of dynamic obstacles
        self.obs3Move = 3 # action of dynamic obstacles
        self.x = [150, 250, 350, 450] * 4 #env position
        self.y = [450] * 4 + [350] * 4 + [250] * 4 + [150] * 4 #env position

        self.terminate_states = dict()
        self.terminate_states[self.obs3] = 1 #Set the obstacle state to 1
        self.terminate_states[self.obs2] = 1 #Set the obstacle state to 1
        self.terminate_states[self.obs1] = 1 #Set the obstacle state to 1

        #action
        self.actions = [0, 1, 2, 3]

        #rewards
        self.rewards = dict()
        self.rewards[str(self.obs1 - 1) + "_3"] = -100.0
        self.rewards[str(self.obs1 - 4) + "_0"] = -100.0
        self.rewards[str(self.obs2 + 1) + "_2"] = -100.0
        self.rewards[str(self.obs2 - 4) + "_0"] = -100.0
        self.rewards[str(self.obs2 - 1) + "_3"] = -100.0
        self.rewards[str(self.obs3 + 1) + "_2"] = -100.0
        self.rewards[str(self.obs3 - 4) + "_0"] = -100.0
        self.rewards[str(self.Terminal - 1) + "_3"] = 100
        self.rewards[str(self.Terminal - 4) + "_0"] = 100

        #Set the state after the action
        self.t = dict()

        self.size = 4

        for i in range(self.size, self.size * self.size): # find the way throught action
            self.t[str(i) + '_1'] = i - 4

        for i in range(self.size * (self.size - 1)):
            self.t[str(i) + '_0'] = i + 4

        for i in range(1, self.size * self.size):
            if i % self.size != 0 :
                self.t[str(i) + '_2'] = i - 1

        for i in range(self.size * self.size):
            if (i + 1) % self.size != 0 :
                self.t[str(i) + '_3'] = i + 1

        self.gamma = 0.8
        self.viewer = None
        self.state = 0

    def _seed(self, seed=None):
        self.np_random, seed = random.seeding.np_random(seed)
        return [seed]

    def getTerminal(self):
        return self.Terminal

    def getGamma(self):
        return self.gamma

    def getStates(self):
        return self.states

    def getAction(self):
        return self.actions

    def getTerminate_states(self):
        return self.terminate_states

    def setAction(self, s):
        self.state = s

    #transition
    def step(self, action):
        self.temp = dict()
        self.temp[self.obs2] = 1
        self.temp[self.obs3] = 1

        # Update terminate states
        self.terminate_states.pop(self.obs2)
        self.terminate_states.pop(self.obs3)

        #set the moving obstacles
        if self.obs3Move == 3: #let obs3 move between 4-6
            if self.obs3 == 6:
                self.obs3 -= 1
                self.obs3Move = 2
            else:
                self.obs3 += 1
        else:
            if self.obs3 == 4:
                self.obs3Move = 3
                self.obs3 += 1
            else:
                self.obs3 -= 1

        if self.obs2Move == 1: #let obs2 move between 1,5,9,13
            if self.obs2 == 1:
                self.obs2Move = 0
                self.obs2 += 4
            else:
                self.obs2 -= 4
        else:
            if self.obs2 == 13:
                self.obs2 -= 4
                self.obs2Move = 1
            else:
                self.obs2 += 4

        self.terminate_states[self.obs2] = 1
        self.terminate_states[self.obs3] = 1

        # Update rewards dictionary
        self.Rewards = dict()
        if self.obs2 == 13:
            self.Rewards[str(self.obs2 - 4) + '_0'] = -100.0
            self.Rewards[str(self.obs2 - 1) + '_3'] = -100.0
            self.Rewards[str(self.obs2 + 1) + '_2'] = -100.0
        elif self.obs2 == 1:
            self.Rewards[str(self.obs2 + 4) + '_1'] = -100.0
            self.Rewards[str(self.obs2 - 1) + '_3'] = -100.0
            self.Rewards[str(self.obs2 + 1) + '_2'] = -100.0
        else:
            self.Rewards[str(self.obs2 - 4) + '_0'] = -100.0
            self.Rewards[str(self.obs2 - 1) + '_3'] = -100.0
            self.Rewards[str(self.obs2 + 1) + '_2'] = -100.0
            self.Rewards[str(self.obs2 + 4) + '_1'] = -100.0

        if self.obs3 == 4:
            self.Rewards[str(self.obs3 - 4) + '_0'] = -100.0
            self.Rewards[str(self.obs3 + 1) + '_2'] = -100.0
            self.Rewards[str(self.obs3 + 4) + '_1'] = -100.0
        else:
            self.Rewards[str(self.obs3 - 4) + '_0'] = -100.0
            self.Rewards[str(self.obs3 - 1) + '_3'] = -100.0
            self.Rewards[str(self.obs3 + 1) + '_2'] = -100.0
            self.Rewards[str(self.obs3 + 4) + '_1'] = -100.0

        self.Rewards[str(self.obs1 - 4) + '_0'] = -100.0
        self.Rewards[str(self.obs1 - 1) + '_3'] = -100.0
        self.Rewards[str(self.obs1 + 4) + '_1'] = -100.0

        self.Rewards[str(self.Terminal - 4) + '_0'] = 100.0
        self.Rewards[str(self.Terminal - 1) + '_3'] = 100.0

        state = self.state
        key = "%d_%d" % (state, action)

        if key in self.t:
            next_state = self.t[key]
        else: #stay because outside the map
            next_state = state
            r = -100.0
            is_terminal = True
            return next_state, r, is_terminal, {}

        self.state = next_state

        is_terminal = False

        #Determine whether the next_ State is where the obstacle is
        if next_state in self.terminate_states or (next_state in self.temp and state in self.terminate_states):
            is_terminal = True

        # if the distance between state and goal state is smaller, give reward. Otherwise get  penalty
        if key not in self.rewards:
            if (self.Terminal - next_state) < (self.Terminal - state):
                r = 70
            else:
                r = -30
        else:
            r = self.rewards[key]

        return next_state, r, is_terminal, {}

    def reset(self):
        self.state = self.states[int(random.random() * len(self.states))]
        return self.state

    #create the env
    def render(self, mode='human'):

        #create screen pixel
        from gym.envs.classic_control import rendering
        screen_width = 600
        screen_height = 600

        if self.viewer is None:
            self.viewer = rendering.Viewer(screen_width, screen_height)

            # creating table
            self.line1 = rendering.Line((100, 100), (500, 100))
            self.line2 = rendering.Line((100, 200), (500, 200))
            self.line3 = rendering.Line((100, 300), (500, 300))
            self.line4 = rendering.Line((100, 400), (500, 400))
            self.line5 = rendering.Line((100, 500), (500, 500))
            self.line6 = rendering.Line((100, 100), (100, 500))
            self.line7 = rendering.Line((200, 100), (200, 500))
            self.line8 = rendering.Line((300, 100), (300, 500))
            self.line9 = rendering.Line((400, 100), (400, 500))
            self.line10 = rendering.Line((500, 100), (500, 500))

            # create obs1
            self.so1 = rendering.make_circle(35)
            self.so1trans = rendering.Transform(translation=(450, 350))
            self.so1.add_attr(self.so1trans)
            self.so1.set_color(0, 0, 0)

            # create obs2
            self.do1 = rendering.make_circle(35)
            self.do1trans = rendering.Transform(translation=(250, 450))
            self.do1.add_attr(self.do1trans)
            self.do1.set_color(1, 0, 0)

            #create obs3
            self.do2 = rendering.make_circle(35)
            self.do2trans = rendering.Transform(translation=(150, 250))
            self.do2.add_attr(self.do2trans)
            self.do2.set_color(1, 0, 0)

            # create Terminal
            self.te = rendering.make_circle(35)
            self.tetrans = rendering.Transform(translation=(450, 450))
            self.te.add_attr(self.tetrans)
            self.te.set_color(0, 0, 1)

            # create robot
            self.robot = rendering.make_circle(30)
            self.robotrans = rendering.Transform(translation=(150, 150))
            self.robot.add_attr(self.robotrans)
            self.robot.set_color(0, 1, 0)

            self.line1.set_color(0, 0, 0)
            self.line2.set_color(0, 0, 0)
            self.line3.set_color(0, 0, 0)
            self.line4.set_color(0, 0, 0)
            self.line5.set_color(0, 0, 0)
            self.line6.set_color(0, 0, 0)
            self.line7.set_color(0, 0, 0)
            self.line8.set_color(0, 0, 0)
            self.line9.set_color(0, 0, 0)
            self.line10.set_color(0, 0, 0)

            self.viewer.add_geom(self.line1)
            self.viewer.add_geom(self.line2)
            self.viewer.add_geom(self.line3)
            self.viewer.add_geom(self.line4)
            self.viewer.add_geom(self.line5)
            self.viewer.add_geom(self.line6)
            self.viewer.add_geom(self.line7)
            self.viewer.add_geom(self.line8)
            self.viewer.add_geom(self.line9)
            self.viewer.add_geom(self.line10)
            self.viewer.add_geom(self.so1)
            self.viewer.add_geom(self.do1)
            self.viewer.add_geom(self.do2)
            self.viewer.add_geom(self.te)
            self.viewer.add_geom(self.robot)

        if self.state is None:
            return None
        return self.viewer.render(return_rgb_array=mode == 'rgb_array')

        if self.viewer:
            self.viewer.close()
            self.viewer = None



