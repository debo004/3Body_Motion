# 3Body_Motion
In physics and classical mechanics, the three-body problem is the problem of taking the initial positions and velocities (or momenta) of three point masses and solving for their subsequent motion according to Newton's laws of motion and Newton's law of universal gravitation .

Newton's laws of motion says that  
### F = ma = GMm/r^2 , 
where G is the Gravitational Constant and the value of G is ,
#### G = 6.67340e-11
And as the value of G is so small so to write code we have to make the equation dimensionless .

## Steps of Solving this problem

1. We need to know the initial positions (x_0, y_0) and velocities (vx_0, vy_0) of the three objects at some starting time, t_0.

2. The gravitational forces acting on each object due to the other two. For object A, the force due to B and C would be calculated as follows (using Newton's law of universal gravitation):
For the force from B:

#### F_(AB) = G * ( m_A * m_B ) / r_(AB)^2

3. Using Newton's second law (F = ma) calculating the accelerations of each object. Dividing the forces by the mass of the object to obtain acceleration vectors:
For object A:

#### a_Ax = F_(AB)x / m_A
#### a_Ay = F_(AB)y / m_A
Repeating this for objects B and C, considering the forces from the other two objects.

4. Numerically integrating the accelerations to obtain velocities and positions over time to update the velocities and positions at small time steps (Δt) forward in time .

#### x_A ( t + △t ) = vx_A ( t ) + ax_A ( t ) * △t
Repeating this for all objects and update their positions and velocities .

5. Continuing the integration process for the desired time duration, repeatedly updating positions and velocities until you reach the desired time t.

6. After integrating for the desired time, you will have the positions (x, y) and velocities (vx, vy) of the three objects at time t.
